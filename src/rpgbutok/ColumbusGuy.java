package rpgbutok;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ColumbusGuy {

    public static Image sector, sectorDos, boi;
    static {
        try {
           
            boi = ImageIO.read(new File("normalrock.jpg"))
                    .getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            sectorDos = ImageIO.read(new File("firegrass.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
            sector = ImageIO.read(new File("badgrass.png"))
                    .getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        } catch (IOException ioe) {
            System.out.println("WHYYYYYYYYYYYYYYYYYYYYYYY");
        }
    }
    int x, y, rX, rY;
    boolean cool, rock;

    
    public ColumbusGuy(int a, int b, boolean bruh) {
        
        x = a;
        y = b;
        cool = bruh;
       
        //B)
    }

    public void paint(Graphics window) {
       
        if (cool) {
            window.drawImage(sector, x, y, null);
        } else {
            window.drawImage(sectorDos, x, y, null);
        }
         if(rock) {
            window.drawImage(boi, x + rX, y + rY,null);
        
        }

    }

}
