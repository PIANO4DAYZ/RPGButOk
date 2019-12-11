package rpgbutok;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class NormalRock {

    /**
     * The hitbox of this NormalRock
     */
    private Rectangle2D.Double hitbox;

    /**
     * An images of an rocks
     */
    private static final Image ROCK, DEAD;

    private static final String[] QUOTES = new String[]{"oh no bro", "coochie",
        "hoho your mom is gOYYYYYYY", "vro not my habanero deviled egos",
        "did you know that 98 percent of\n"
        + "virgins within the american states\n"
        + "actually originate frtom the small\n"
        + "southern country of the middle east,\n"
        + "when the arabians took over lord gigalent.\n"
        + "He was a evil and dangerous man, y el\n"
        + "era muyyyyyyyyyyyyyyyyyyyy disordenado.\n"
        + "Pero, el es muy guapo, y es un aguacate."
    };

    /**
     * The quote of this rock
     */
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
            temp = ImageIO.read(new File("deadrock.png"))
                    .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(NormalRock.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DEAD = temp;
        }

    }
    private boolean there;
    private int x;
    private int y;
    private boolean isAlive;

    public NormalRock(int a, int b, boolean c) {
        this.quote = QUOTES[(int) (Math.random() * QUOTES.length)];
        x = a;
        y = b;
        there = c;
        hitbox = new Rectangle2D.Double(a, b, 30, 30);
        isAlive = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics window) {
        window.drawImage(getImage(), x, y, null);
    }

    public boolean intersects(Rectangle2D other) {
        return hitbox.intersects(other);
    }

    private Image getImage() {
        return isAlive ? ROCK : DEAD;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isThere() {
        return there;
    }

    public String getQuote() {
        return quote;
    }
}
