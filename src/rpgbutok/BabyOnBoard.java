package rpgbutok;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BabyOnBoard {

    private static final BufferedImage BABY;

    static {
        BufferedImage temp = null;
        try {
            temp = ImageIO.read(new File("theAMMUNITION.png"));
        } catch (IOException ioe) {
        } finally {
            BABY = temp;
        }
    }
    private Rectangle2D.Double hitbox;
    private int posX, posY, size, dy, dx;

    public BabyOnBoard(int x, int y) {
        size = (int) (Math.random() * 50);
        dy = 0;
        dx = 0;

        posX = x;
        posY = y;
        int v = 10;
        double theta = Math.random() * Math.PI / 3 - Math.PI / 6;
        double tempX = Math.cos(theta) * v, tempY = Math.sin(theta) * v;
        dx = (int) (tempX > 0 ? Math.ceil(tempX) : Math.floor(tempX));
        dy = (int) (tempY > 0 ? Math.ceil(tempY) : Math.floor(tempY));
        hitbox = new Rectangle2D.Double(x, y, size, size);
    }

    public void moveBaby() {
        // posY += (int) (Math.random() * 20 - 10);//dy;
        posY += dy;
        posX += dx;
        // posY += (int) (Math.random() * 20 - 10);//dx;
    }

    public void paint(Graphics window) {

        window.drawImage(BABY, posX/* + (25 - size) / 2*/, posY, size, size, null);
    }

    public int getY() {
        return posY;
    }

    /*public boolean intersects(Rectangle2D other) {
        return hitbox.intersects(other);
    }*/
    public int getSize() {
        return size;

    }

    public int getX() {
        return posX;
    }

    public int getDX() {
        return dx;
    }

    public void setDX(int dx) {
        this.dx = dx;
    }

}
