package rpgbutok;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * A class for utilities
 *
 * @author Jed Wang
 */
public class Utilities {

    /**
     * Returns the file stored at the given path
     *
     * @param path the path to find the File at
     * @return a File object that represent the file at the given path
     * @throws URISyntaxException if the path given cannot be converted into an
     * URI
     */
    public static File getFile(String path) throws URISyntaxException {
        URL url = Utilities.class.getResource(path);
        return new File(url.toURI());
    }

    /**
     * Reads the file at the given file path and returns the Image stored at the
     * given path
     *
     * @param path the path of the Image to read
     * @return an Image object that represents the read image
     * @throws IOException the standard IOException reasons
     */
    public static BufferedImage getImage(String path) throws IOException {
        URL url = Utilities.class.getResource(path);
        return ImageIO.read(url);
    }

    /**
     * Reads the file at the given file path and returns the Image stored at the
     * given path. If an exception is thrown, {@code null} is returned.
     *
     * @param path the path of the Image to read
     * @return an Image object that represents the read image, or {@code null}
     * is returned if an Exception is thrown
     */
    public static BufferedImage getImageSafe(String path) {
        try {
            return getImage(path);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
