package rpgbutok;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;

class Utilities {
    public static File getFile(String path) throws URISyntaxException {
        URL url = Utilities.class.getResource(path);
        return new File(url.toURI());
    }
    
    public static BufferedImage getImage(String path) throws IOException {
        URL url = Utilities.class.getResource(path);
        return ImageIO.read(url);
    }
    
    public static BufferedImage getImageSafe(String path) {
        try {
            return getImage(path);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}