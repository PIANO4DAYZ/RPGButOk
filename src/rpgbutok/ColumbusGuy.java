package rpgbutok;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class ColumbusGuy {

    private Rectangle2D.Double hitbox;
    public static Image sectorLevels[], sectorBrailes[], exit, boi, exit2;

    static {
        try {
            boi = ImageIO.read(new File("normalrock.jpg"))
                    .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            exit = ImageIO.read(new File("entrancodebancho.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            exit2 = ImageIO.read(new File("exitodepremesito.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorBrailes = new Image[5];
            for (int i = 0; i < 5; i++) {
                sectorBrailes[i] = ImageIO.read(new File("firegrass" + (i + 1) + ".png"))
                        .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            }
            // System.out.println(Arrays.toString(sectorBrailes));
            sectorLevels = new Image[5];
            for (int i = 0; i < 5; i++) {
                sectorLevels[i] = ImageIO.read(new File("badgrass" + (i + 1) + ".png"))
                        .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            }
            // System.out.println(Arrays.toString(sectorLevels));
        } catch (IOException ioe) {
            System.out.println("WHYYYYYYYYYYYYYYYYYYYYYYY");
        }
    }

    private int x, y, rX, rY, level;
    private boolean cool, eggsit, changed;

    public ColumbusGuy(int a, int b, int i, boolean bruh, boolean eggsit) {
        if (eggsit) {
            hitbox = new Rectangle2D.Double(a, b, 160, 160);
        }
        changed = false;
        x = a;
        y = b;
        level = i;
        cool = bruh;
        this.eggsit = eggsit;
        //B)
    }

    public void paint(Graphics window) {
        //welcome to the paint method :)
        if (cool) {
            window.drawImage(sectorLevels[level], x, y, null);
        } else {
            window.drawImage(sectorBrailes[level], x, y, null);
        }
        if (eggsit) {
            window.drawImage(exit, x + rX, y + rY, null);
        }
        if (changed) {
            window.drawImage(exit2, x + rX, y + rY, null);
        }
    }

    public void setExit() {
        changed = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean intersects(Rectangle2D other) {
        return hitbox.intersects(other);
    }

    public boolean getExit() {
        return eggsit;
    }
}
