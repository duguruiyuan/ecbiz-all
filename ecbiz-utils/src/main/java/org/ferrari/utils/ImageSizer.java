package org.ferrari.utils;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class ImageSizer {

	 public static final MediaTracker tracker = new MediaTracker(new Component() {
	        private static final long serialVersionUID = 1234162663955668507L;} );
	    
	    /**
	     * @param in 图像的字节数组
	     * @param out 输出流
	     * @param width 图像宽
	     * @param height 图像高
	     * 图片格式  PNG
	     * @throws IOException
	     */
	    public static void resize(byte[] in, OutputStream out, int width, int height) throws IOException {
	        resize(in, out, width, height, java.awt.Image.SCALE_DEFAULT, "PNG");
	    }
	    /**
	     * @param in 图像的字节数组
	     * @param out 输出流
	     * @param width 图像宽
	     * @param height 图像高
	     * @param format 图片格式 JPG, PNG
	     * @throws IOException
	     */
	    public static void resize(byte[] in, OutputStream out, int width, int height, String format) throws IOException {
	        resize(in, out, width, height, java.awt.Image.SCALE_DEFAULT, format);
	    }
	    /**
	     * @param in 图像的字节数组
	     * @param out 输出流
	     * @param width 图像宽
	     * @param height 图像高
	     * @param hints Hints are one of SCALE_DEFAULT, SCALE_FAST, SCALE_SMOOTH,
	     *  SCALE_REPLICATE, SCALE_AREA_AVERAGING as defined in java.awt.Image.
	     * @param format 图片格式 JPG, PNG
	     * @throws IOException
	     */
	    public static void resize(byte[] in, OutputStream out, int width, int height, int hints, String format) throws IOException {
	        Image inputImage = Toolkit.getDefaultToolkit().createImage( in );
	        waitForImage( inputImage );
	        int imageWidth = inputImage.getWidth( null );
	        if ( imageWidth < 1 ) 
	           throw new IllegalArgumentException( "image width " + imageWidth + " is out of range" );
	        int imageHeight = inputImage.getHeight( null );
	        if ( imageHeight < 1 ) 
	           throw new IllegalArgumentException( "image height " + imageHeight + " is out of range" );
	        
	        // Create output image.
	        double scaleW = (double) imageWidth / (double) width;
	        double scaleY = (double) imageHeight / (double) height;
	        if (scaleW >= 0 && scaleY >=0) {
	            if (scaleW > scaleY) {
	                height = -1;
	            } else {
	                width = -1;
	            }
	        }
	        Image outputImage = inputImage.getScaledInstance( width, height, hints );
	        checkImage( outputImage );
	        
	        encode( out, outputImage, format );
	        
	    }
	    

	    /** Checks the given image for valid width and height. */
	    private static void checkImage( Image image ) {
	       waitForImage( image );
	       int imageWidth = image.getWidth( null );
	       if ( imageWidth < 1 ) 
	          throw new IllegalArgumentException( "image width " + imageWidth + " is out of range" );
	       int imageHeight = image.getHeight( null );
	       if ( imageHeight < 1 ) 
	          throw new IllegalArgumentException( "image height " + imageHeight + " is out of range" );
	       // System.out.println( "Image size=" + imageWidth + "x" + imageHeight );
	    } // checkImage

	    /** Waits for given image to load. Use before querying image height/width/colors. */
	    private static void waitForImage( Image image ) {
	       try {
	          tracker.addImage( image, 0 );
	          tracker.waitForID( 0 );
	          // loadStatus = tracker.statusID( 0, false );
	          tracker.removeImage(image, 0);
	       } catch( InterruptedException e ) { e.printStackTrace(); }
	    } // waitForImage

	    /** Encodes the given image at the given quality to the output stream. */
	    private static void encode( OutputStream outputStream, Image outputImage, String format ) 
	       throws java.io.IOException {
	       int outputWidth  = outputImage.getWidth( null );
	       if ( outputWidth < 1 ) 
	          throw new IllegalArgumentException( "output image width " + outputWidth + " is out of range" );
	       int outputHeight = outputImage.getHeight( null );
	       if ( outputHeight < 1 ) 
	          throw new IllegalArgumentException( "output image height " + outputHeight + " is out of range" );

	       // Get a buffered image from the image.
	       BufferedImage bi = new BufferedImage( outputWidth, outputHeight,
	          BufferedImage.TYPE_INT_RGB );                                                   
	       Graphics2D biContext = bi.createGraphics();
	       biContext.drawImage( outputImage, 0, 0, null );
//	       // Note that additional drawing such as watermarks or logos can be placed here.
	//
//	       // com.sun.image.codec.jpeg package is included in sun and ibm sdk 1.3
//	       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder( outputStream );
//	       // The default quality is 0.75.
//	       JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam( bi );
//	       jep.setQuality( outputQuality, true );
//	       encoder.encode( bi, jep );
	       // encoder.encode( bi );
//	     输出图象到页面
	       ImageIO.write(bi, format, outputStream);
	       outputStream.flush();      
	    } // encodeImage
}
