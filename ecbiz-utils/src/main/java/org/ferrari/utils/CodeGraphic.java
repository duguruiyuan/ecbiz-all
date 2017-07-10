package org.ferrari.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

/**
 * 改进后的验证码工具
 * @author kevin
 *
 */
public class CodeGraphic {
	private double cy = 1, cx = 0.6, toCx = 0.3;
	private static double width = 100;
	private static double height = 30;
	private static int fontSize = 20;// 字符大小
	//随机字符数组
	private final static char[] CHAR_RANGE = { 'A', 'B', 'C', 'D', 'E', 'F',
		'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S',
		'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
		'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's',
		't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5',
		'6', '7', '8', '9' };
//	private final static char[] CHAR_RANGE = { 'A', 'B', 'C', 'D', 'E', 'F',
//		'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
//		'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
//		'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
//		't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5',
//		'6', '7', '8', '9' };

	// 最大字符串个数
	private static final int MAX_CHARCOUNT = 16;

	// 要生成的字符个数，由工厂方法得到
	private int charCount = 0;
	
	private static Color c1 = new Color(71, 141, 141);
	private static Color c2 = new Color(84, 41, 41);
	private static Color c3 = new Color(90, 138, 0);
	private static Color c4 = new Color(64, 0, 64);
	private static Color c5 = new Color(0, 0, 66);
	// 颜色数组，绘制字串时随机选择一个
	private static final Color[] CHAR_COLOR = { c1, c2, c3, c4, c5 };
	private static final String[] CHAR_FONT = { 
								"Monospaced", 
								"Credit valley",
								"Bell MT", 
								"nyala"
							};
	// 随机数生成器
	private Random r = new Random();

	/**
	 * 生成图像的格式常量，JPEG格式,生成为文件时扩展名为.jpg； 输出到页面时需要设置MIME type 为image/jpeg
	 */
	public static String GRAPHIC_JPEG = "JPEG";
	/**
	 * 生成图像的格式常量，PNG格式,生成为文件时扩展名为.png； 输出到页面时需要设置MIME type 为image/png
	 */
	public static String GRAPHIC_PNG = "PNG";

	// 用工厂方法创建对象
	protected CodeGraphic(int charCount) {
		this.charCount = charCount;
	}

	/**
	 * 创建对象的工厂方法
	 * @param charCount 要生成的字符个数，个数在1到16之间
	 * Return 返回CodeGraphic对象实例
	 * @throws Exception 参数charCount错误时抛出
	 */
	public static CodeGraphic createInstance(int charCount) throws Exception {
		if (charCount < 1 || charCount > MAX_CHARCOUNT) {
			throw new Exception("Invalid parameter charCount,charCount should between in 1 and 16");
		}
		return new CodeGraphic(charCount);
	}
	/**
	 * 
	 * @param charCount 字符数
	 * @param w 宽
	 * @param h 高
	 * @param fsize 字体大小
	 * @return
	 * @throws Exception
	 */
	public static CodeGraphic createInstance(int charCount, int w, int h, int fsize) throws Exception {
		if(fsize >= h) fontSize = h;
		else fontSize = fsize;
		width = w; height = h; 
		if (charCount < 1 || charCount > MAX_CHARCOUNT) {
			throw new Exception("Invalid parameter charCount,charCount should between in 1 and 16");
		}
		return new CodeGraphic(charCount);
	}

	/**
	 * 随机生成一个数字串，并以图像方式绘制，绘制结果输出到流out中
	 * @param graphicFormat 设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
	 * @param out 图像结果输出流
	 * @return 随机生成的串的值
	 * @throws IOException
	 */
	public String drawNumber(String graphicFormat, OutputStream out)
			throws IOException {
		// 随机生成的串的值
		String charValue = "";
		charValue = randNumber();
		return draw(charValue, graphicFormat, out);

	}
	/**
	 * 随机生成一个字母串，并以图像方式绘制，绘制结果输出到流out中
	 * @param graphicFormat 设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
	 * @param out 图像结果输出流
	 * @return 随机生成的串的值
	 * @throws IOException
	 */
	public String drawAlpha(String graphicFormat, OutputStream out)
			throws IOException {
		// 随机生成的串的值
		String charValue = "";
		charValue = randAlpha();
		return draw(charValue, graphicFormat, out);
	}

	/**
	 * 以图像方式绘制字符串，绘制结果输出到流out中
	 * @param charValue 要绘制的字符串
	 * @param graphicFormat 设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
	 * @param out 图像结果输出流
	 * @return 随机生成的串的值
	 * @throws IOException
	 */
	protected String draw(String charValue, String graphicFormat,
			OutputStream out) throws IOException {
		// 创建内存图像区
		BufferedImage bi = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = bi.createGraphics();
		// 设置背景色
		Color backColor = Color.WHITE;
		g.setBackground(backColor);
		g.fillRect(0, 0, (int) width, (int) height);
		
		g.setColor(Color.GRAY);//设置边框颜色
		g.drawRect(1, 1, (int)width-2,(int)height-2);//绘制边框
		
		Random random = new Random();
		g.setColor(new Color(129, 192, 192));
		//绘制正弦干扰曲线
		for (double i = -Math.PI / cx; i < Math.PI / cx; i += 0.1) {
			if (Math.abs(cx - toCx) < 0.1) toCx = random.nextDouble() * 2;
			cx += (toCx - cx) / 100;
			double x = r.nextDouble();
			int sx = translateX(i), sy = translateY(sin(i-x)), ex = translateX(i + 0.1), ey = translateY(sin(i + 0.1));
			for (int k = 0; k < 5; k++) {
				sy += 1; ey += 1; 
				g.drawLine(sx, sy, ex, ey);
			}
		}
		// 绘制字符, 每个字符颜色随机
		for (int i = 0; i < charCount; i++) {
			String c = charValue.substring(i, i + 1);
			Color colors = CHAR_COLOR[randomInt(0, CHAR_COLOR.length)];
			String font = CHAR_FONT[randomInt(0, CHAR_FONT.length)];// 设置font
			//System.out.println(font);
			g.setFont(new Font(font, Font.BOLD | Font.ITALIC, fontSize));
			g.setColor(colors);
			int xpos = (int) ((i + 1) * (width / (charCount + 1))-6);
			int x = (int) (fontSize - 4);
			int y = (int) (fontSize + 4 > height ? height : fontSize + 4);// 垂直方向上随机
			int ypos = randomInt(x, y);
			g.drawString(c, xpos, ypos);
		}
		g.dispose();
		bi.flush();
		ImageIO.write(bi, graphicFormat, out);// 输出到流
		return charValue;
	}

	protected String randNumber() {
		String charValue = "";
		// 生成随机数字串
		for (int i = 0; i < charCount; i++) {
			charValue += String.valueOf(randomInt(0, 10));
		}
		return charValue;
	}

	private String randAlpha() {
		String charValue = "";
		// 生成随机字母串
		for (int i = 0; i < charCount; i++) {
			char t = CHAR_RANGE[randomInt(0, 58)];
			charValue += String.valueOf(t);
		}
		return charValue;
	}

	/**
	 * 返回[from,to)之间的一个随机整数
	 * @param from 起始值
	 * @param to 结束值
	 * @return [from,to)之间的一个随机整数
	 */
	protected int randomInt(int from, int to) {
		return from + r.nextInt(to - from);
	}

	private int translateX(double x) {
		return (int) (x * width / Math.PI / 4 + width / 2);
	}

	private int translateY(double y) {
		return (int) (height / 2 - y * width / Math.PI / 4);
	}

	private double sin(double x) {
		return (cy * Math.sin(cx * x));
	}
	/**
	 * 
	 * @param size 字符数
	 * @param stream
	 * @return
	 * @throws IOException
	 */
	public String createImage(OutputStream stream) throws IOException {
		try {
			return createInstance(charCount, (int)width, (int)height, fontSize).drawAlpha(GRAPHIC_PNG, stream);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void saveSession(HttpSession session, String type, String code) {
		if(code == null) return;
		session.setAttribute("ValidateImage_" + type, code.toLowerCase());
	}
	
	public static boolean validate(HttpSession session, String type, String code) {
		if (code == null) return false;
		return code.toLowerCase().equals(session.getAttribute("ValidateImage_" + type));
	}
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//System.out.println(CodeGraphic.createInstance(5, 80, 25, 20).createImage(new FileOutputStream("c:/myimg.png")));
	}

}