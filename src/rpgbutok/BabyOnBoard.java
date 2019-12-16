package rpgbutok;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class BabyOnBoard {

    private static final BufferedImage BABY;
    private static final HashMap<Integer, Image> BABY_CACHE = new HashMap<>();
    private static final Random RAND = new Random();

    static {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(new File("theTransAMMUNITION.png"));
        } catch (IOException ioe) {
        } finally {
            BABY = temp;
        }
    }

    private Rectangle2D.Double hitbox;
    private double posX, posY, dy, dx;
    private int size;

    public BabyOnBoard(int x, int y) {
        size = RAND.nextInt(49) + 1;
        dy = 0;
        dx = 0;

        posX = x;
        posY = y;
        int v = 10;
        double theta = RAND.nextDouble() * Math.PI / 3 - Math.PI / 6;
        dx = Math.cos(theta) * v;
        dy = Math.sin(theta) * v;
        hitbox = new Rectangle2D.Double(x, y, size, size);
    }

    public void moveBaby() {
        posY += dy;
        posX += dx;
    }

    public void paint(Graphics window) {
        Graphics2D g2D = (Graphics2D) window;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.drawImage(getBaby(size), (int) posX, (int) posY, size, size, null);
    }

    public double getY() {
        return posY;
    }

    /*public boolean intersects(Rectangle2D other) {
        return hitbox.intersects(other);
    }*/

    public int getSize() {
        return size;
    }

    public double getX() {
        return posX;
    }

    public double getDX() {
        return dx;
    }

    public void setDX(double dx) {
        this.dx = dx;
    }

    private static Image getBaby(int size) {
        if (BABY_CACHE.containsKey(size)) {
            return BABY_CACHE.get(size);
        } else {
            Image temp = BABY.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            BABY_CACHE.put(size, temp);
            return temp;
        }
    }
}
