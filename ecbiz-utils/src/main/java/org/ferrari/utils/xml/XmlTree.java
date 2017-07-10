package org.ferrari.utils.xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/** 
 * <p>XmlTree.java</p> 
 * <pre>
 * Only generate tree structure for "DTree" web component.
 * this class is only adapt to DTree component.
 * </pre>
 */
public class XmlTree {
	/**
	 * Construtor
	 */
	public XmlTree() {
	}
	/**
	 * used as sequence of DTree
	 */
	private int rootIndex = 0;
	/**
	 * Get DTree structure according to the xml.
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public String getDTree(String xml) throws Exception {
		Document doc = XmlUtility.stringToDocument(xml);
		Element elm = doc.getDocumentElement();
		return getDTree(elm, 0).toString();
	}
	private static String escapeJavascript(String string) {
		StringBuffer sb = new StringBuffer(string.length());
		// true if last char was blank
		boolean lastWasBlankChar = false;
		int len = string.length();
		char c;
		char pc = '\0';
		for (int i = 0; i < len; i++) {
			c = string.charAt(i);
			if (c == ' ') {
				// blank gets extra work,
				// this solves the problem you get if you replace all
				// blanks with &nbsp;, if you do that you loss 
				// word breaking
				if (lastWasBlankChar) {
					lastWasBlankChar = false;
					sb.append("&nbsp;");
				} else {
					lastWasBlankChar = true;
					sb.append(' ');
				}
			} else {
				lastWasBlankChar = false;
				//
				// HTML Special Chars
				if (c == '"')
					sb.append("&quot;");
				else if (c == '\'')
					sb.append("\"");					
				else if (c == '&')
					sb.append("&amp;");
				else if (c == '<')
					sb.append("&lt;");
				else if (c == '>')
					sb.append("&gt;");
				else if (c == '\r')
					// Handle Newline
					sb.append("&lt;BR&gt;");
				else if (c == '\n' ){
					if(pc != '\r')
					    sb.append("&lt;BR&gt;");
				}											
				else if (c == '\\')
					// Handle Newline
					sb.append("\\\\");	
				else {
					int ci = 0xffff & c;
					if (ci < 160)
						// nothing special only 7 Bit
						sb.append(c);
					else {
						// Not 7 Bit use the unicode system
						sb.append("&#");
						sb.append(new Integer(ci).toString());
						sb.append(';');
					}
				}
			}
			pc=c;
		}
		/*
		 * if need cut the too long string. 
		StringBuffer buf=new StringBuffer();
		if (sb.length() > 120) {
			buf.append("<span title=\"").append(sb).append("\">").append(sb.substring(0, 117)).append("...</span>");
			return buf.toString();
		}*/
		return sb.toString();
	}

	/**
	 * escape xml
	 * @param val
	 * @return
	 */
	private String escapeXml(String val) {
		if (val == null)
			return null;
		return val.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	/**
	 * escape DTree special xml, convert "'" to "\'"
	 * and convert "\r\n" and "\r" and "\n" to " "(one space) 
	 * @param val
	 * @return
	 */
	protected String escapeDTreeXml(String val) {
		if (val == null)
			return null;
		return escapeXml(val).replaceAll("'", "\\\"").replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ");
	}
	/**
	 * Get DTree structure from node.
	 * @param node
	 * @param root
	 * @return
	 * @throws Exception
	 */
	private StringBuffer getDTree(Node node, int root) throws Exception {
		if (node == null)
			return (null);
		StringBuffer buf = new StringBuffer();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			String nodeName = node.getNodeName();
			String nodeValue = XmlUtility.getSingleValue(node, ".");
			if (nodeValue == null) {
				if (!node.hasChildNodes()) {
					buf.append("dtree.add(" + rootIndex + "," + (root - 1) + ",'" + nodeName + "=(null)');");
				} else {
					buf.append("dtree.add(" + rootIndex + "," + (root - 1) + ",'" + nodeName + "');");
				}

			} else {
				buf.append(
					"dtree.add("
						+ rootIndex
						+ ","
						+ (root - 1)
						+ ",'"
						+ nodeName
						+ "="
						+ escapeJavascript(nodeValue)
						+ "');");
			}

			rootIndex++;
			root = rootIndex;
			NodeList nodeList = node.getChildNodes();
			if (nodeList != null && nodeList.getLength() > 0) {
				for (int i = 0; i < nodeList.getLength(); ++i) {
					StringBuffer sb = getDTree(nodeList.item(i), root);
					if (sb != null) {
						buf.append(sb);
					}
				}
			}
			return (buf);
		}
		return null;
	}
	/**
	 * For test.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(escapeJavascript("<Begin>Jiangbing</Begin>\n\n\nOK i love you.sdafasdfafdasfasfdsafsaffasddddddddddddddddddddddddddddddddddddddaad  dasfasdfasfd afdafdsafsadfasc  deafasfd"));
	}
}
