package rpgbutok;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Vore1255 {

    private int x, y, z, h;
    private static final BufferedImage bruh
            = Utilities.getImageSafe("/resources/oh no.png");

    public Vore1255(int a, int b, int c) {
        x = a;
        y = b;
        z = c;
    }

    public void paint(Graphics g) {
        g.drawImage(bruh, x, y, z, z, null);
    }
}
