/**
 * 
 */
package org.ferrari.utils.image;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.axis.utils.ByteArrayOutputStream;

/**
 * @author anly
 * 
 */
public class ThumImageUtil {

	static BufferedImage WATERMARK_RIGHT = null;
	static BufferedImage WATERMARK_LEFT = null;

	static {
		try {
			WATERMARK_RIGHT = ImageIO.read(ThumImageUtil.class.getResourceAsStream("chexun_logo_1.png"));
			WATERMARK_LEFT = ImageIO.read(ThumImageUtil.class.getResourceAsStream("chexun_black_1.gif"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void cutImage(byte[] srcBytes, BufferedOutputStream out, int width, int height, boolean force, String formatName) {

		try {
			ByteArrayInputStream in = new ByteArrayInputStream(srcBytes); // 将b作为输入流；
			BufferedImage image = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();

			int srcH = image.getHeight();
			int srcW = image.getWidth();

			Rectangle area = null;

			if (force) {
				area = getMaxCutSize(srcW, srcH, width, height);
			} else {
				// 计算缩放尺寸
				area = getMaxZoomSize(srcW, srcH, width, height);
			}

			int finalWidth = force ? width : area.width;
			int finalHeight = force ? height : area.height;

			BufferedImage toImage = Thumbnails.of(image).size(finalWidth, finalHeight).outputQuality(0.9).asBufferedImage();

			ImageIO.write(toImage, formatName, out);
			out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static byte[] cutImage(byte[] srcBytes, int width, int height, boolean force, String formatName) {

		byte[] result = null;
		try {

			ByteArrayInputStream in = new ByteArrayInputStream(srcBytes); // 将b作为输入流；
			BufferedImage image = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();

			int srcH = image.getHeight();
			int srcW = image.getWidth();

			Rectangle area = null;

			if (force) {
				area = getMaxCutSize(srcW, srcH, width, height);
			} else {
				// 计算缩放尺寸
				area = getMaxZoomSize(srcW, srcH, width, height);
			}

			int finalWidth = force ? width : area.width;
			int finalHeight = force ? height : area.height;

			BufferedImage toImage = Thumbnails.of(image).size(finalWidth, finalHeight).outputQuality(0.9).asBufferedImage();

			// result = ((DataBufferByte)
			// toImage.getData().getDataBuffer()).getData();

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(toImage, formatName, out);
			result = out.toByteArray();
			out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static int getWidth(byte[] srcBytes) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(srcBytes); // 将b作为输入流；
			BufferedImage image = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
			return image.getWidth();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int getHeight(byte[] srcBytes) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(srcBytes); // 将b作为输入流；
			BufferedImage image = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
			return image.getHeight();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// pass
	public static byte[] pressImage(byte[] src_image, String formatName) {

		byte[] result = null;
		try {

			ByteArrayInputStream in = new ByteArrayInputStream(src_image); // 将b作为输入流；
			BufferedImage image = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();

			BufferedImage toImage = Thumbnails.of(image).outputQuality(0.9).watermark(Positions.BOTTOM_RIGHT, WATERMARK_RIGHT, 0.9f).scale(1.0).asBufferedImage();

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(toImage, formatName, out);
			result = out.toByteArray();
			out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static byte[] pressImage(File picture, String formatName) {

		byte[] result = null;
		try {

			BufferedImage image = ImageIO.read(picture);

			BufferedImage toImage = Thumbnails.of(image).outputQuality(0.9).watermark(Positions.BOTTOM_RIGHT, WATERMARK_RIGHT, 0.9f).scale(1.0).asBufferedImage();

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(toImage, formatName, out);
			result = out.toByteArray();
			out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static void pressImage(byte[] src_image, OutputStream out, String formatName) {

		try {

			ByteArrayInputStream in = new ByteArrayInputStream(src_image); // 将b作为输入流；
			BufferedImage image = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();

			BufferedImage toImage = Thumbnails.of(image).outputQuality(0.9).watermark(Positions.TOP_LEFT, WATERMARK_LEFT, 0.9f).watermark(Positions.BOTTOM_RIGHT, WATERMARK_RIGHT, 0.9f).scale(1.0).asBufferedImage();

			ImageIO.write(toImage, formatName, out);
			out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static Rectangle getMaxCutSize(int w1, int h1, int w2, int h2) {
		int x, y, w3, h3;
		if ((float) w1 / w2 >= (float) h1 / h2) {
			h3 = h1; // 高度设为原高度
			w3 = h1 * w2 / h2; // 宽度按比率计算
		} else {
			w3 = w1; // 宽度设为原宽度
			h3 = w1 * h2 / w2; // 高度按比较计算
		}
		x = (w1 - w3) / 2;
		y = (h1 - h3) / 2;
		return new Rectangle(x > 0 ? x : 0, y > 0 ? y : 0, w3, h3);
	}

	private static Rectangle getMaxZoomSize(int w1, int h1, int w2, int h2) {
		int w3, h3;
		if (w1 <= w2 && h1 <= h2) {
			w3 = w1;
			h3 = h1;
		} else if ((float) w1 / w2 >= (float) h1 / h2) {
			w3 = w2;
			h3 = w2 * h1 / w1;
		} else {
			h3 = h2;
			w3 = h2 * w1 / h1;
		}
		return new Rectangle(w3, h3);
	}

}
