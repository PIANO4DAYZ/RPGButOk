package rpgbutok;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Vore1255 {

    int x, y, z, h;
    private static final BufferedImage bruh;

    static {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(new File("oh no.png"));
        } catch (IOException ioe) {
            System.err.println("sout oh no");
        } finally {
            bruh = temp;
        }

    }

    public Vore1255(int a, int b, int c) {
        x = a;
        y = b;
        z = c;

    }

    public void paint(Graphics g) {
        g.drawImage(bruh, x, y, z, z, null);
    }
}
