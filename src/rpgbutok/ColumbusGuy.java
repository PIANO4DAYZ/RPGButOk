package rpgbutok;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ColumbusGuy {
    private Rectangle2D.Double hitbox;
    public static Image sector, sectorDos, exit, boi;
    static {
        try {
           
            boi = ImageIO.read(new File("normalrock.jpg"))
                    .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                exit = ImageIO.read(new File("sexdungeon.png"))
                .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sectorDos = ImageIO.read(new File("firegrass.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sector = ImageIO.read(new File("badgrass.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        } catch (IOException ioe) {
            System.out.println("WHYYYYYYYYYYYYYYYYYYYYYYY");
        }
    }
    int x, y, rX, rY;
    boolean cool, eggsit;

    
    public ColumbusGuy(int a, int b, boolean bruh, boolean eggsit) {
        if(eggsit)
            hitbox = new Rectangle2D.Double(a, b, 160, 160);
        x = a;
        y = b;
        cool = bruh;
        this.eggsit = eggsit;
        //B)
    }

    public void paint(Graphics window) {
       
        if (cool) {
            window.drawImage(sector, x, y, null);
        } else {
            window.drawImage(sectorDos, x, y, null);
        }
         if(eggsit) {
            window.drawImage(exit, x + rX, y + rY,null);
        
        }

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
