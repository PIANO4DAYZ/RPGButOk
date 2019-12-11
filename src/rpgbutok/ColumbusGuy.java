package rpgbutok;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ColumbusGuy {

    private Rectangle2D.Double hitbox;
    public static Image sectorLevel, sectorLevel2, sectorLevel3, sectorLevel4,
            sectorLevel5, sectorBraile, sectorBraile2, sectorBraile3,
            sectorBraile4, sectorBraile5, exit, boi, exit2;

    static {
        try {
            boi = ImageIO.read(new File("normalrock.jpg"))
                    .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            exit = ImageIO.read(new File("entrancodebancho.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            exit2 = ImageIO.read(new File("exitodepremesito.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorBraile = ImageIO.read(new File("firegrass.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorBraile2 = ImageIO.read(new File("firegrass2.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorBraile3 = ImageIO.read(new File("firegrass3.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorBraile4 = ImageIO.read(new File("firegrass4.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorBraile5 = ImageIO.read(new File("firegrass5.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorLevel = ImageIO.read(new File("badgrass.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorLevel2 = ImageIO.read(new File("badgrass2.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorLevel3 = ImageIO.read(new File("badgrass3.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorLevel4 = ImageIO.read(new File("badgrass4.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorLevel5 = ImageIO.read(new File("badgrass5.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
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
            switch (level) {
                case 0:
                    window.drawImage(sectorLevel, x, y, null);
                    break;
                case 1:
                    window.drawImage(sectorLevel2, x, y, null);
                    break;
                case 2:
                    window.drawImage(sectorLevel3, x, y, null);
                    break;
                case 3:
                    window.drawImage(sectorLevel4, x, y, null);
                    break;
                case 4:
                    window.drawImage(sectorLevel5, x, y, null);
                    break;
                default:
                    window.drawString("BRUH", x, y);
                    break;
            }
        } else {
            switch (level) {
                case 0:
                    window.drawImage(sectorBraile, x, y, null);
                    break;
                case 1:
                    window.drawImage(sectorBraile2, x, y, null);
                    break;
                case 2:
                    window.drawImage(sectorBraile3, x, y, null);
                    break;
                case 3:
                    window.drawImage(sectorBraile4, x, y, null);
                    break;
                case 4:
                    window.drawImage(sectorBraile5, x, y, null);
                    break;
                default:
                    window.drawString("BRUH", x, y);
                    break;
            }
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
