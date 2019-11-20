package rpgbutok;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class NormalRock {

    private Rectangle2D.Double hitbox;
    private static final Image ROCK, DEAD;
    private String quote;

    static {
        Image temp = null;
        try {
            temp = ImageIO.read(new File("normalrock.jpg"))
                    .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(NormalRock.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ROCK = temp;
        }
        try {
            temp = ImageIO.read(new File("deadrock.png")).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(NormalRock.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DEAD = temp;
        }

    }
    boolean there;
    int x;
    int y;
    Image rock;

    public NormalRock(int a, int b, boolean c, String quote) throws IOException {
        this.quote = quote;
        x = a;
        y = b;
        there = c;
        hitbox = new Rectangle2D.Double(a, b, 30, 30);
        rock = ROCK;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics window) {

        window.drawImage(rock, x, y, null);
    }

    public boolean intersects(Rectangle2D other) {
        return hitbox.intersects(other);
    }

    public void live() throws IOException {
        rock = ROCK;
    }

    public void die() throws IOException {
        rock = DEAD;
    }

    public boolean hmm() {
        return there;
    }

    public String getQuote() {
        return quote;
    }
}
