package bll;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageProcessing {
	public final static String JPEG = "jpeg";
	public final static String JPG = "jpg";
	public final static String GIF = "gif";
	public final static String PNG = "png";

	public Image resizeImage(Image originalImage, int targetWidth, int targetHeight) {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
		return resultingImage;
	}

	public byte[] convertImageToByteArray(Image img, String type) throws IOException {
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, type, baos);
		byte[] imgByte = baos.toByteArray();

		return imgByte;
	}

	public Image convertByteArrayToImage(byte[] array, String type) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(array);
		BufferedImage bi = ImageIO.read(bais);

		Image img = bi.getScaledInstance(bi.getWidth(), bi.getHeight(), Image.SCALE_SMOOTH);
		return img;
	}

	public String getImageExtension(File f) {
		String ext = null;
		String getFileName = f.getName();
		int i = getFileName.lastIndexOf('.');

		if (i > 0 && i < getFileName.length() - 1)
			ext = getFileName.substring(i + 1).toLowerCase();

		return ext;
	}
}
