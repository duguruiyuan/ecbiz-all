package org.ferrari.utils.image;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

/*
 * 
 * @Create: 2008-12-23 上午11:14:33     
 * @Description: TODO
 * 
 * 
 * 得到系统的相关路径
 * 
 * @see: com.jmyz.cms.common.util
 * @modify by: 宋燕豪
 * @time: 2008-12-23 上午11:14:33
 */
public class Util {

	public Util() {
	}

	private static void byte2hex(byte b, StringBuffer buf) {
		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		int high = ((b & 0xf0) >> 4);
		int low = (b & 0x0f);
		buf.append(hexChars[high]);
		buf.append(hexChars[low]);
	}

	public static String toHexStr(byte[] t, int start, int end) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < t.length; i++) {
			if (start <= i && end >= i) {
				if (t[i] != 0) {
					byte2hex(t[i], sb);
					sb.append(" ");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Splits the string on every token into an array of stack frames.
	 * 
	 * @param string
	 *            the string
	 * @param onToken
	 *            the token
	 * @return the resultant array //@deprecated This is an internal utility
	 *         method that should not be used
	 */
	public static String[] splitString(String string, String onToken) {
		if (string == null)
			return null;
		final StringTokenizer tokenizer = new StringTokenizer(string, onToken);
		final String[] result = new String[tokenizer.countTokens()];

		for (int i = 0; i < result.length; i++) {
			result[i] = tokenizer.nextToken();
		}

		return result;
	}

	public static String getWebModulPath() {
		String ret = getWebinfPath();
		int cnt = ret.indexOf("/WEB-INF");
		if (cnt > -1)
			ret = ret.substring(0, cnt);
		return ret;
	}

	private static String _webinfPath = null;

	public static String getWebinfPath() {
		if (_webinfPath == null) {
			String res = null;
			Util u = new Util();
			String classname = u.getClass().getName().replace('.', '/')
					+ ".class";
			ClassLoader cl = u.getClass().getClassLoader();
			if (cl != null) {
				java.net.URL url = cl.getResource(classname);
				if (url != null) {
					String path = url.getFile();
					int fileStrPosition = path.indexOf("file:/");
					int begin = 0;
					int end = path.length();

					if (fileStrPosition >= 0)
						begin = fileStrPosition + 5;

					// 先判断是否是未打包文件

					end = path.indexOf("classes/" + classname);
					if (end < 0) {
						// 如果是在webModule下面的jar包

						end = path.indexOf("lib/");
						if (end < 0) {
							// 在普通目录下的jar包

							int tmpend = path.indexOf("!/");
							end = path.substring(0, tmpend).lastIndexOf("/");
						}
					}
					String rf = path.substring(begin, end);
					res = new File(rf).getAbsolutePath().replace('\\', '/')
							+ "/";
				}
			}
			try {
				_webinfPath = java.net.URLDecoder.decode(res, "UTF-8");
			} catch (UnsupportedEncodingException ex) {
			}
			// System.out.println("WEB-INF Path=" + _webinfPath);
		}
		return _webinfPath;
	}

	private static String _systemPath = null;

	public static String getSystemPath() {
		if (_systemPath == null) {
			String res = getWebinfPath();
			if (res.indexOf("WEB-INF/") > 0 && res.length() > 10) {
				res = res.substring(0, res.lastIndexOf("/", res.length() - 12))
						+ "/";
			}
			_systemPath = res;
			// System.out.println("System Path=" + _systemPath);
		}
		return _systemPath;
	}

	@SuppressWarnings("static-access")
	public static String getWebRoot() {
		String path = new Util().getWebinfPath();
		path = path.substring(0, path.length()
				- new String("WEB-INF/").length());
		System.out.println(path);
		return path;
	}

	@SuppressWarnings("static-access")
	public static String getWebClass() {
		String path = new Util().getWebinfPath();
		return path + "classes/";
	}

	@SuppressWarnings("static-access")
	public static void main(String ar[]) {
		String system_path = new Util().getWebinfPath();
		System.out.println(system_path);
		String system_path2 = Util.getSystemPath();
		System.out.println(system_path2);
		System.out.println(new Util().getWebClass());

	}
}
