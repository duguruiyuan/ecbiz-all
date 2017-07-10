package org.ferrari.utils.image;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class DwindlePic {
	String InputDir; // 输入图路径

	String OutputDir; // 输出图路径

	String InputFileName; // 输入图文件名

	String OutputFileName; // 输出图文件名

	int OutputWidth = 80; // 默认输出图片宽

	int OutputHeight = 80; // 默认输出图片高

	int rate = 0;

	boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)

	public DwindlePic() {
		// 初始化变量
		InputDir = "";
		OutputDir = "";
		InputFileName = "";
		OutputFileName = "";
		OutputWidth = 80;
		OutputHeight = 80;
		rate = 0;
	}

	public boolean s_pic() {
		// BufferedImage image;
		// String NewFileName;
		// 建立输出文件对象
		File file = new File(OutputDir + OutputFileName);
		FileOutputStream tempout = null;
		try {
			tempout = new FileOutputStream(file);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		Image img = null;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Applet app = new Applet();
		MediaTracker mt = new MediaTracker(app);
		try {
			img = tk.getImage(InputDir + InputFileName);
			mt.addImage(img, 0);
			mt.waitForID(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (img.getWidth(null) == -1) {
			// System.out.println(" can't read,retry!" + "<BR>");
			return false;
		} else {
			int new_w;
			int new_h;
			if (this.proportion == true) { // 判断是否是等比缩放.
				// 为等比缩放计算输出的图片宽度及高度
				double rate1 = ((double) img.getWidth(null))
						/ (double) OutputWidth + 0.1;
				double rate2 = ((double) img.getHeight(null))
						/ (double) OutputHeight + 0.1;
				double rate = rate1 > rate2 ? rate1 : rate2;
				new_w = (int) (((double) img.getWidth(null)) / rate);
				new_h = (int) (((double) img.getHeight(null)) / rate);
			} else {
				new_w = OutputWidth; // 输出的图片宽度
				new_h = OutputHeight; // 输出的图片高度
			}
			// new_w=200;
			; // new_h=150;
			BufferedImage buffImg = new BufferedImage(new_w, new_h,
					BufferedImage.TYPE_INT_RGB);

			Graphics g = buffImg.createGraphics();

			g.setColor(Color.white);
			g.fillRect(0, 0, new_w, new_h);

			g.drawImage(img, 0, 0, new_w, new_h, null);
			g.dispose();

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(tempout);
			try {
				encoder.encode(buffImg);
				tempout.close();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}
		}
		return true;
	}

	public boolean s_pic(String InputDir, String OutputDir,
			String InputFileName, String OutputFileName) {
		// 输入图路径
		this.InputDir = InputDir;
		// 输出图路径
		this.OutputDir = OutputDir;
		// 输入图文件名
		this.InputFileName = InputFileName;
		// 输出图文件名
		this.OutputFileName = OutputFileName;
		return s_pic();
	}

	public boolean s_pic(String InputDir, String OutputDir,
			String InputFileName, String OutputFileName, int width, int height,
			boolean gp) {
		// 输入图路径
		this.InputDir = InputDir;
		// 输出图路径
		this.OutputDir = OutputDir;
		// 输入图文件名
		this.InputFileName = InputFileName;
		// 输出图文件名
		this.OutputFileName = OutputFileName;
		// 设置图片长宽
		setW_H(width, height);
		// 是否是等比缩放 标记
		this.proportion = gp;
		return s_pic();
	}

	public void setInputDir(String InputDir) {
		this.InputDir = InputDir;
	}

	public void setOutputDir(String OutputDir) {
		this.OutputDir = OutputDir;
	}

	public void setInputFileName(String InputFileName) {
		this.InputFileName = InputFileName;
	}

	public void setOutputFileName(String OutputFileName) {
		this.OutputFileName = OutputFileName;
	}

	public void setOutputWidth(int OutputWidth) {
		this.OutputWidth = OutputWidth;
	}

	public void setOutputHeight(int OutputHeight) {
		this.OutputHeight = OutputHeight;
	}

	public void setW_H(int width, int height) {
		this.OutputWidth = width;
		this.OutputHeight = height;
	}

	// ///////
	/*
	 * data_source des
	 * 
	 * 
	 * force 是自动缩放； force=true ;将按照设定的参数缩放；
	 */
	private void cutImage(BufferedImage image, String destFile, int width,
			int height, boolean force) throws IOException {
		Image imgTemp = null;
		Rectangle area = null;

		if (force) {
			area = getMaxCutSize(image.getWidth(), image.getHeight(), width,
					height);
			ImageFilter filter = new CropImageFilter(area.x, area.y,
					area.width, area.height);
			ImageProducer producer = new FilteredImageSource(image.getSource(),
					filter);
			imgTemp = Toolkit.getDefaultToolkit().createImage(producer);
		} else {
			// 计算缩放尺寸
			area = getMaxZoomSize(image.getWidth(), image.getHeight(), width,
					height);
			imgTemp = image;
		}

		int finalWidth = force ? width : area.width;
		int finalHeight = force ? height : area.height;

		// 绘出缩放后图片
		imgTemp = imgTemp.getScaledInstance(finalWidth, finalHeight,
				Image.SCALE_SMOOTH);
		BufferedImage imgResult = new BufferedImage(finalWidth, finalHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = imgResult.getGraphics();
		g.drawImage(imgTemp, 0, 0, null);
		g.dispose();

		// 输出文件
		FileOutputStream out = new FileOutputStream(destFile);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(imgResult);
		param.setQuality(0.90f, true);
		encoder.encode(imgResult, param);
		out.close();
	}

	/*
	 * 
	 * force 如果为false是等比例缩放；
	 * 
	 */
	public void cutImage(InputStream in, BufferedOutputStream out, int width,
			int height, boolean force) throws IOException {
		Image imgTemp = null;
		Rectangle area = null;
		BufferedImage image = ImageIO.read(in);
		if (force) {
			area = getMaxCutSize(image.getWidth(), image.getHeight(), width,
					height);
			ImageFilter filter = new CropImageFilter(area.x, area.y,
					area.width, area.height);
			ImageProducer producer = new FilteredImageSource(image.getSource(),
					filter);
			imgTemp = Toolkit.getDefaultToolkit().createImage(producer);
		} else {
			// 计算缩放尺寸
			area = getMaxZoomSize(image.getWidth(), image.getHeight(), width,
					height);
			imgTemp = image;
		}
		int finalWidth = force ? width : area.width;
		int finalHeight = force ? height : area.height;
		// 绘出缩放后图片
		imgTemp = imgTemp.getScaledInstance(finalWidth, finalHeight,
				Image.SCALE_SMOOTH);
		BufferedImage imgResult = new BufferedImage(finalWidth, finalHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = imgResult.getGraphics();
		g.drawImage(imgTemp, 0, 0, null);
		g.dispose();

		// 输出文件
		
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(imgResult);
		param.setQuality(0.90f, true);
		encoder.encode(imgResult, param);
	}
	
	//
	public byte[] cutImage(InputStream in, int width,
			int height, boolean force) throws IOException {
		Image imgTemp = null;
		Rectangle area = null;
		BufferedImage image = ImageIO.read(in);
		if (force) {
			area = getMaxCutSize(image.getWidth(), image.getHeight(), width,
					height);
			ImageFilter filter = new CropImageFilter(area.x, area.y,
					area.width, area.height);
			ImageProducer producer = new FilteredImageSource(image.getSource(),
					filter);
			imgTemp = Toolkit.getDefaultToolkit().createImage(producer);
		} else {
			// 计算缩放尺寸
			area = getMaxZoomSize(image.getWidth(), image.getHeight(), width,
					height);
			imgTemp = image;
		}
		int finalWidth = force ? width : area.width;
		int finalHeight = force ? height : area.height;
		// 绘出缩放后图片
		imgTemp = imgTemp.getScaledInstance(finalWidth, finalHeight,
				Image.SCALE_SMOOTH);
		BufferedImage imgResult = new BufferedImage(finalWidth, finalHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = imgResult.getGraphics();
		g.drawImage(imgTemp, 0, 0, null);
		g.dispose();

		// 输出文件
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(imgResult);
		param.setQuality(0.90f, true);
		encoder.encode(imgResult, param);
		
		byte temp[] = null;
		temp = out.toByteArray();
		out.close();
		return temp;
	}

	private Rectangle getMaxCutSize(int w1, int h1, int w2, int h2) {
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

	/**
	 * 图片缩放后大小
	 * 
	 * @param w1
	 * @param h1
	 * @param w2
	 * @param h2
	 * @return
	 */
	private Rectangle getMaxZoomSize(int w1, int h1, int w2, int h2) {
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

	public static void saveImageAsJpgResize(String source, String des, int w,
			int h) throws IOException {
		DwindlePic mypic = new DwindlePic();
		BufferedImage image = ImageIO.read(new File(source));
		mypic.cutImage(image, des, w, h, true);
	}

	// ////////
	public static void main(String[] a) throws IOException {
		// s_pic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度)
		DwindlePic mypic = new DwindlePic();
		String inFilePath = "D:\\1011040835f4cd1df875079d53.jpg";
		String outFilePath = "D:\\aa.JPG";
		File file = new File(inFilePath);
		@SuppressWarnings("unused")
		File outfile = new File(outFilePath);
		FileInputStream inputfile = new FileInputStream(file);
		BufferedImage image = ImageIO.read(inputfile);
		mypic.cutImage(image, outFilePath, 135, 105, false);
		// ImageUtil.doCompressQuality("D:\\temp\\aaa.jpg",
		// "D:\\temp\\bbbbb.jpg",
		// (float) 1.1);
		System.out.print("wancheng ");
	}
}
