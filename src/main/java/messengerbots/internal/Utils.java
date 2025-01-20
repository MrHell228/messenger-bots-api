package messengerbots.internal;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

import com.vk.api.sdk.objects.photos.Photo;

final class Utils {
	
	public static Dimension imageDimension(File imgFile) throws IOException {
		int pos = imgFile.getName().lastIndexOf(".");
		if (pos == -1) {
			throw new IOException("No extension for file: " + imgFile.getAbsolutePath());
		}
		
		String suffix = imgFile.getName().substring(pos + 1);
		Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
		while (iter.hasNext()) {
			ImageReader reader = iter.next();
			try {
				ImageInputStream stream = new FileImageInputStream(imgFile);
				reader.setInput(stream);
				int width = reader.getWidth(reader.getMinIndex());
				int height = reader.getHeight(reader.getMinIndex());
				return new Dimension(width, height);
			} catch (IOException e) {
				// log.warn("Error reading: " + imgFile.getAbsolutePath(), e);
				System.out.println("Error reading: " + imgFile.getAbsolutePath());
				e.printStackTrace();
			} finally {
				reader.dispose();
			}
		}

		throw new IOException("Not a known image file: " + imgFile.getAbsolutePath());
	}
	
	public static URL toURL(final URI uri) {
		try {
			return uri.toURL();
		} catch (MalformedURLException e) {
			throw ExceptionUtil.url(e);
		}
	}
	
	public static String vkPhotoToString(final Photo photo) {
		final String result = "photo" + photo.getOwnerId() + "_" + photo.getId();
		if (photo.getAccessKey() != null) {
			return result + "_" + photo.getAccessKey();
		} else {
			return result;
		}
	}
	
	private Utils() {
	}
}
