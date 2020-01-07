package rpgbutok;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * A totally normal rock.
 *
 * @author Brighton Liu, Jed Wang
 */
public class NormalRock {

    /**
     * The hitbox of this NormalRock
     */
    private Rectangle2D.Double hitbox;

    /**
     * An images of an rocks
     */
    private static final Image ROCK, DEAD;

    /**
     * An immutable array of quotes that the rock knows.
     */
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
        ROCK = Utilities.getImageSafe("/resources/normalrock.jpg")
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        DEAD = Utilities.getImageSafe("/resources/deadrock.png")
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    }

    /**
     * The x-coordinate of this rock
     */
    private int x;
    /**
     * The y-coordinate of this rock
     */
    private int y;
    /**
     * Whether this rock is alive or not
     */
    private boolean isAlive;

    /**
     * Creates a new rock
     *
     * @param a the x-coordinate of the rock
     * @param b the y-coordinate of the rock
     */
    public NormalRock(int a, int b) {
        this.quote = QUOTES[(int) (Math.random() * QUOTES.length)];
        x = a;
        y = b;
        hitbox = new Rectangle2D.Double(a, b, 30, 30);
        isAlive = true;
    }

    /**
     * Returns the x-coordinate of this rock
     *
     * @return the x-coordinate of this rock
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this rock
     *
     * @return the y-coordinate of this rock
     */
    public int getY() {
        return y;
    }

    /**
     * Paints this NormalRock
     *
     * @param window the Graphics to use
     */
    public void paint(Graphics window) {
        window.drawImage(getImage(), x, y, null);
    }

    /**
     * Determines whether the given Rectangle intersects with this NormalRock's hitbox
     *
     * @param other the other hitbox to check
     * @return whether this NormalRock intersects with the given Rectangle
     */
    public boolean intersects(Rectangle2D other) {
        return hitbox.intersects(other);
    }

    /**
     * Returns the image to use to draw this NormalRock
     *
     * @return the image to use to draw this NormalRock
     */
    private Image getImage() {
        return isAlive ? ROCK : DEAD;
    }

    /**
     * Sets whether this rock is alive or not
     *
     * @param isAlive whether this rock is to be alive or not
     */
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    /**
     * Returns the quote that this NormalRock uses.
     *
     * @return the quote that this NormalRock uses.
     */
    public String getQuote() {
        return quote;
    }
}
