package com.proceso.desarrollo.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

public class UtilImage {

	public static boolean isValidImage(byte[] data) {
		if (data == null || data.length == 0)
			return false;
		try (ByteArrayInputStream bais = new ByteArrayInputStream(data)) {
			BufferedImage img = ImageIO.read(bais);
			return img != null;
		} catch (Exception e) {
			return false;
		}
	}
}
