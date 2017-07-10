package org.ferrari.utils.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.XPath;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.ferrari.exception.UtilsException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

public class Dom4jUtil {
	/**
	 * logger
	 */
	private static Logger logger = Logger.getLogger(Dom4jUtil.class.getName());
	/** XML输入流 */
	private InputStream inputStream;

	/** 解析的XML Document */
	private Document document;

	/** XML根元素 */
	private Element root;

	/** 用于命名空间操作 */
	private HashMap<String, Object> xmlMap;

	/** 缺省命名空间，即如<root xmlns="url"> */
	private String nsKey; // ns

	private String nsURL; // http://....

	private String ns = ""; // like '//ns:', start with the xpath

	
	private static final String DEFAULT_ENCODING = "UTF-8";
	/** XML编码，缺省为UTF-8 */
	private String encoding = DEFAULT_ENCODING;
	public Dom4jUtil() {
		document = DocumentHelper.createDocument();
	}

	public Dom4jUtil(InputStream inputStream) {
		document = DocumentHelper.createDocument();
		this.inputStream = inputStream;
	}

	public void open() {
		try {
			SAXReader reader = new SAXReader();
			document = reader.read(inputStream);
			root = document.getRootElement();
		} catch (Exception e) {
			logger.error("format xml", e);
			throw new UtilsException("format xml", e);
		}
	}

	/** <code>setNamespace("ns","http://..>");</code> */
	public void setNamespace(String key, String url) {
		nsKey = key;
		nsURL = url;
		ns = "//" + key + ":";
		xmlMap = new HashMap<String, Object>();
		xmlMap.put(key, url);
	}
	
	// ----------------------------------------------------------------------------------------
	/**
	 * Get object by namespace and xpath. like: node1/node2/node3 return an
	 * element node1/node2/node3/@attribute return an attribute
	 */
	public Object selectSingleObject(Element parent, String path) {
		String attribute = null;
		String[] aAttribute = path.split("@");

		String sNode = aAttribute[0];
		if (aAttribute.length > 1) {
			attribute = aAttribute[1];
			sNode = sNode.substring(0, sNode.length() - 1);
		}
		if (attribute != null)
			return ((Element) selectObject(parent, parsePath(sNode, false))).attribute(attribute);
		else
			return selectObject(parent, parsePath(sNode, false));
	}

	public Object selectSingleObject(String path) {
		String attribute = null;
		String[] aAttribute = path.split("@");

		String sNode = aAttribute[0];
		if (aAttribute.length > 1) {
			attribute = aAttribute[1];
			sNode = sNode.substring(0, sNode.length() - 1);
		}
		if (attribute != null)
			return ((Element) selectObject(parsePath(sNode, true))).attribute(attribute);
		else
			return selectObject(parsePath(sNode, true));
	}

	/**
	 * parse xpath to path with namespace
	 * 
	 * @param path
	 *            node1/node2/node3
	 * @param full
	 *            true - //ns:node1/ns:node2/ns:node3 false - ns:node1/ns:node2/ns:node3
	 * @return
	 */
	private String parsePath(String path, boolean full) {
		String[] nodes = path.split("/");
		StringBuffer tmp = new StringBuffer();
		for (int i = 0; i < nodes.length; i++) {
			tmp.append("/" + nsKey + ":" + nodes[i]);
		}
		return full ? "/" + tmp.toString() : tmp.substring(1);
	}
	public List<?> selectObjects(Element parent, String path) {
		path = parsePath(path, false);
		XPath xpath = parent.createXPath(path);
		xpath.setNamespaceURIs(xmlMap);
		return xpath.selectNodes(parent);
	}

	public List<?> selectObjects(String path) {
		path = parsePath(path, true);
		XPath xpath = document.createXPath(path);
		xpath.setNamespaceURIs(xmlMap);
		return xpath.selectNodes(document);
	}

	/** Select node by parent element and sub-path. */
	private Object selectObject(Element parent, String path) {
		XPath xpath = parent.createXPath(path);
		xpath.setNamespaceURIs(xmlMap);
		return xpath.selectSingleNode(parent);
	}

	private Object selectObject(String path) {
		XPath xpath = document.createXPath(path);
		xpath.setNamespaceURIs(xmlMap);
		return xpath.selectSingleNode(document);
	}

	// ----------------------------------------------------------------------------------------
	/** Node name which is unique in the document. */
	public Element selectSingleElement(String path) {
		XPath xpath = document.createXPath(ns + path);
		xpath.setNamespaceURIs(xmlMap);
		return (Element) xpath.selectSingleNode(document);
	}
	// ----------------------------------------------------------------------------------------
	/**
	 * Add root element.
	 * 
	 * @param rootName
	 *            the name of the root element.
	 */
	public void addRoot(String rootName) {
		root = document.addElement(rootName);
	}

	/**
	 * Add namespace to the root element.
	 * 
	 * @param url
	 *            the url of namespace.
	 */
	public Namespace addNamespace(String url) {
		nsURL = url;
		Namespace ns = new Namespace("", url);
		root.add(ns);
		return ns;
	}

	/**
	 * Add namespace to the root element with name
	 * 
	 * @param name
	 *            Namespace name
	 * @param url
	 *            Namespace url
	 */
	public Namespace addNamespace(String key, String url) {
		Namespace ns = new Namespace(key, url);
		root.add(ns);
		return ns;
	}

	/**
	 * Add subnode of root if namespace, then create subnode void it.
	 * 
	 * @param nodeName
	 * @return
	 */
	public Element addElement(String nodeName) {
		if (nsURL != null)
			return root.addElement(nodeName, nsURL);
		else
			return root.addElement(nodeName);
	}

	/**
	 * Output the document to byte[].
	 * 
	 * @return byte[] xml content
	 */
	public byte[] toBytes() {
		try {
			OutputFormat format = new OutputFormat("\t", true);
			format.setEncoding(encoding);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);

			return out.toByteArray();
		} catch (UnsupportedEncodingException uee) {
			System.out.println(uee.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		return null;
	}

	// ----------------------------------------------------------------------------------------
	public Document getDocument() {
		return document;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	// ----------------------------------------------------------------------------------------
	/** print the xml tree. */
	public void printXmlTree() {
		printElement(root, 0);
	}

	private void printElement(Element element, int level) {
		// print indent
		for (int i = 0; i < level; i++) {
			System.out.print(" ");
		}
		System.out.println(element.getQualifiedName() + "=="
				+ element.getTextTrim());
		Iterator<?> iter = element.elementIterator();
		while (iter.hasNext()) {
			Element sub = (Element) iter.next();
			printElement(sub, level + 2);
		}
	}

	public static NodeList selectNodeList(Node contextNode, String childNodePath)
			throws Exception {
		return (XPathAPI.selectNodeList(contextNode, childNodePath));
	}

	/**
	 * select a Node list in context Node
	 * 
	 * @param contextNode
	 *            is the context Node in which to select child nodes
	 * @param childNodePath
	 *            is the child node XPath
	 * @param namespaceNode
	 *            is the name space node
	 * @return a Node list
	 */
	public static NodeList selectNodeList(Node contextNode,
			String childNodePath, Node namespaceNode) throws Exception {
		return (XPathAPI.selectNodeList(contextNode, childNodePath,
				namespaceNode));
	}

	/**
	 * select a single Node in context Node, namespace is from contextNode.
	 * 
	 * @param contextNode
	 *            is the context Node in which to select child nodes
	 * @param childNodePath
	 *            is the child node XPath
	 * @return the first Node found in the context node
	 */
	public static Node selectSingleNode(Node contextNode, String childNodePath)
			throws Exception {
		return (XPathAPI.selectSingleNode(contextNode, childNodePath));
	}

	/**
	 * select a single Node in context Node
	 * 
	 * @param contextNode
	 *            is the context Node in which to select child nodes
	 * @param childNodePath
	 *            is the child node XPath
	 * @param namespaceNode
	 *            is the name space node
	 * @return the first Node found in the context node
	 */
	public static Node selectSingleNode(Node contextNode, String childNodePath,
			Node namespaceNode) throws Exception {
		return (XPathAPI.selectSingleNode(contextNode, childNodePath,
				namespaceNode));
	}

	/**
	 * select a Node list in context Node, namespace is from contextNode.
	 * 
	 * @param contextNode
	 *            is the context Node in which to select child nodes
	 * @param childNodePath
	 *            is the child node XPath
	 * @return a Node list as NodeIterator
	 */
	public static NodeIterator selectNodeIterator(Node contextNode,
			String childNodePath) throws Exception {
		return (XPathAPI.selectNodeIterator(contextNode, childNodePath));
	}

	/**
	 * select a Node list in context Node
	 * 
	 * @param contextNode
	 *            is the context Node in which to select child nodes
	 * @param childNodePath
	 *            is the child node XPath
	 * @param namespaceNode
	 *            is the name space node
	 * @return a Node list as NodeIterator
	 */
	public static NodeIterator selectNodeIterator(Node contextNode,
			String childNodePath, Node namespaceNode) throws Exception {
		return (XPathAPI.selectNodeIterator(contextNode, childNodePath,
				namespaceNode));
	}

	public static void formatXmlFile(String filename) {
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(filename));
			XMLWriter writer = null;
			/** 格式化输出,类型IE浏览一样 */
			OutputFormat format = OutputFormat.createPrettyPrint();
			/** 指定XML编码 */
			format.setEncoding("GBK");
			writer = new XMLWriter(new FileWriter(new File(filename)), format);
			writer.write(document);
			writer.close();
			/** 执行成功,需返回1 */
		} catch (Exception e) {
			logger.error("format xml", e);
			throw new UtilsException("format xml", e);
		}
	}
	public static String formatXml(Document document) {
		return formatXml(document, DEFAULT_ENCODING);
	}
	public static String formatXml(Document document, String encoding) {
		StringWriter writer = new StringWriter();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(encoding==null ? DEFAULT_ENCODING : encoding);
		XMLWriter xmlwriter = new XMLWriter(writer, format);
		try {
			xmlwriter.write(document);
		} catch (Exception e) {
			logger.error("format xml", e);
			throw new UtilsException("format xml", e);
		}
		return writer.toString();
	}
	public static String formatXml(String xml) {
		if (xml == null || xml.trim().length() == 0) {
			return null;
		}
		return formatXml(xml, DEFAULT_ENCODING);
	}
	public static String formatXml(String xml, String encoding) {
		if (xml == null || xml.trim().length() == 0) {
			return null;
		}
		try {
			Document document = DocumentHelper.parseText(xml);
			return formatXml(document, encoding);
		} catch (DocumentException e) {
			logger.error("format xml", e);
			throw new UtilsException("format xml", e);
		}
	}	
	// ----------------------------------------------------------------------------------------
	public static void main(String[] args) {
		String nsUrl = "http://www.ofbase.net/test";
		String nsName = "ns";

		String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<test xmlns=\"http://www.ofbase.net/test\">"
				+ " <node11 a=\"a1\"/>" + " <node12 a=\"a2\">"
				+ "   <node21>text1234</node21>" + " </node12>"
				+ " <node13 a=\"a3\">" + "   <node21>text2</node21>"
				+ "   <node22 a2=\"attribute value\">text3</node22>"
				+ " </node13>" + "</test>";

		/** <?xml version="1.0" encoding="UTF-8"?>
			<test xmlns="http://www.ofbase.net/test">
				<node11 a="a1" />
				<node12 a="a2">
					<node21>text1234</node21>
				</node12>
				<node13 a="a3">
					<node21>text2</node21>
					<node22 a2="attribute value">text3</node22>
				</node13>
			</test>
		*/
		Dom4jUtil dom = new Dom4jUtil(new ByteArrayInputStream(s.getBytes()));
		dom.open();
		dom.setNamespace(nsName, nsUrl);
		Element e = dom.selectSingleElement("node13");
		Element e1 = (Element) dom.selectSingleObject(e, "node22");
		System.out.println(e1.getText());
		System.out.println(e1.attributeValue("a2"));
		System.out.println("---------------------------------------");
		Element e2 = (Element) dom.selectSingleObject(e, "node21");
		System.out.println(e2.getText());
		System.out.println("---------------------------------------");
		e = (Element) dom.selectSingleObject("test/node13/node22");
		System.out.println(e.attributeValue("a2"));
	}
}
