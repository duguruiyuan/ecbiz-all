package org.ferrari.utils.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/*
 * 
 * @Create: 2009-4-21 上午09:16:11     
 * @Description: TODO
 * @see: com.jmyz.action.newcar.action.bsm.util
 * @modify by: 宋燕豪
 * @time: 2009-4-21 上午09:16:11
 */
public final class ImageWaterMark {

	static byte WATER_MARKS[] = new byte[1024 * 10];
	static byte WATER_MARKS_LEFT[] = new byte[1024 * 10];
	static {
		InputStream input_water = ImageWaterMark.class
				.getResourceAsStream("chexun_white.gif");
		
		InputStream input_water2 = ImageWaterMark.class
		.getResourceAsStream("chexun_black.gif");
		
		try {
			input_water.read(WATER_MARKS);
			input_water.close();
			
			input_water2.read(WATER_MARKS_LEFT);
			input_water2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ImageWaterMark() {
	}

	public final static byte[] pressImage(byte src_image[]) {
		byte temp[] = null;
		try {
			int x = 5, y = 5;
			ByteArrayInputStream bin = new ByteArrayInputStream(WATER_MARKS);
			Image water_marker = ImageIO.read(bin);
			
			ByteArrayInputStream bin2 = new ByteArrayInputStream(WATER_MARKS_LEFT);
			Image water_marker_left = ImageIO.read(bin2);
			
			ByteArrayInputStream file_image = new ByteArrayInputStream(
					src_image);
			Image src = ImageIO.read(file_image);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);

			
			
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_BGR

			);
			Graphics g = image.createGraphics();
			src = src.getScaledInstance(wideth, height,
					Image.SCALE_SMOOTH);
			g.drawImage(src, 0, 0, wideth, height, null);

			int wideth_watermarke = water_marker.getWidth(null);
			int height_watermarke = water_marker.getHeight(null);
			water_marker = water_marker.getScaledInstance(wideth, height,
					Image.SCALE_SMOOTH);
			g.drawImage(water_marker, wideth - wideth_watermarke - x, height
					- height_watermarke - y, wideth_watermarke,
					height_watermarke, null);
			
			wideth_watermarke = water_marker_left.getWidth(null);
			height_watermarke = water_marker_left.getHeight(null);
			
			water_marker_left = water_marker_left.getScaledInstance(wideth, height,
					Image.SCALE_SMOOTH);
			
			g.drawImage(water_marker_left, x, y, wideth_watermarke,
					height_watermarke, null);
			
			
			g.dispose();
			// FileOutputStream out = new FileOutputStream(targetImg);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
			param.setQuality(0.90f, true);
			encoder.encode(image,param);
			temp = out.toByteArray();
			out.close();
			bin.close();
			bin2.close();
			file_image.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public final static void pressImage2(byte src_image[], OutputStream out) {
		try {
			int x = 5, y = 5;
			ByteArrayInputStream bin = new ByteArrayInputStream(WATER_MARKS);
			Image water_marker = ImageIO.read(bin);
			
			ByteArrayInputStream bin2 = new ByteArrayInputStream(WATER_MARKS_LEFT);
			Image water_marker_left = ImageIO.read(bin2);
			
			ByteArrayInputStream file_image = new ByteArrayInputStream(
					src_image);
			Image src = ImageIO.read(file_image);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);

			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_BGR

			);
			Graphics g = image.createGraphics();

			src = src.getScaledInstance(wideth, height,
					Image.SCALE_SMOOTH);
			g.drawImage(src, 0, 0, wideth, height, null);

			int wideth_watermarke = water_marker.getWidth(null);
			int height_watermarke = water_marker.getHeight(null);
			
			water_marker = water_marker.getScaledInstance(wideth, height,
					Image.SCALE_SMOOTH);
			g.drawImage(water_marker, wideth - wideth_watermarke - x, height
					- height_watermarke - y, wideth_watermarke,
					height_watermarke, null);
			
			wideth_watermarke = water_marker_left.getWidth(null);
			height_watermarke = water_marker_left.getHeight(null);
			
			water_marker_left = water_marker_left.getScaledInstance(wideth, height,
					Image.SCALE_SMOOTH);
			g.drawImage(water_marker_left, x, y, wideth_watermarke,
					height_watermarke, null);
			
			
			g.dispose();
			// FileOutputStream out = new FileOutputStream(targetImg);
			
			
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
			param.setQuality(0.90f, true);
			encoder.encode(image,param);
			encoder.encode(image);
			
			bin.close();
			bin2.close();
			file_image.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public final static void pressImage(String pressImg, String targetImg,
			int x, int y) {
		try {
			String path = new Util().getWebinfPath();
			path = path.substring(0, path.length()
					- new String("WEB-INF/").length())
					+ "watermark.gif";
			// String path = fullpath+"/watermark.gif";
			File _file = new File(targetImg);
			if (!_file.canRead()) {
				return;
			}
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);

			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);

			File _filewatermarke = new File(path);
			Image src_watermarke = ImageIO.read(_filewatermarke);
			int wideth_watermarke = src_watermarke.getWidth(null);
			int height_watermarke = src_watermarke.getHeight(null);
			g.drawImage(src_watermarke, wideth - wideth_watermarke - x, height
					- height_watermarke - y, wideth_watermarke,
					height_watermarke, null);
			// /
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void pressText(String pressText, String targetImg,
			String fontName, int fontStyle, int color, int fontSize, int x,
			int y) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// String s="www.qhd.com.cn";
			g.setColor(Color.RED);
			g.setFont(new Font(fontName, fontStyle, fontSize));

			g.drawString(pressText, wideth - fontSize - x, height - fontSize
					/ 2 - y);
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void maina(String[] args) {

		String str = "D:\\pic\\test";
		@SuppressWarnings("unused")
		String test_file = "D:\\pic\\aaa\\bb\\test\\";
		File file = new File(str);
		if (file.exists()) {
			File[] f = file.listFiles();
			if (file.isDirectory()) {
				for (int i = 0; i < f.length; i++) {
					if (f[i].getName().indexOf("JPG") == -1
							&& f[i].getName().indexOf("jpg") == -1)
						continue;
					System.out.println(f[i].getName() + "name");
					pressImage("", f[i].getPath(), 20, 20);
					// ImageUtils.pressImage("",f[i].getName(), 20, 20);
					//
					// DwindlePic.saveImageAsJpgResize(f[i].getPath(), test_file
					// + "\\" + f[i].getName(), 135, 105);

				}

			}

		}

		// String file =
		// "D:\\apache-tomcat-6.0\\apache-tomcat-6.0.16\\webapps\\jmyzweb\\photo\\1236577450541.jpg";
		// pressImage("D:\\pic\\pic_produce\\pic\\aa.JPG",
		// "D:\\pic\\pic_produce\\pic\\aa____.JPG");

		System.out.println("OK");
		System.out.println(WATER_MARKS.length);
	}

	public static void main(String[] args) throws IOException {

		String src = "D:\\IMG_2135.JPG";
		String dest = "D:\\5.jpg";
		InputStream in = new FileInputStream(new File(src));
		byte[] src_image = new byte[1024 * 1024];
		in.read(src_image);
		in.close();

		byte[] bout = ImageWaterMark.pressImage(src_image);
		FileOutputStream fout = new FileOutputStream(new File(dest));
		
//		ImageWaterMark.pressImage2(src_image,fout);
		
		fout.write(bout);
		fout.close();
		System.out.println("aaaaaaaa");
	}
}