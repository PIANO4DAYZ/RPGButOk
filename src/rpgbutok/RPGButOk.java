package rpgbutok;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public final class RPGButOk extends JPanel {

    //BabyOnBoard vro = null;
    ColumbusGuy[][] finalmap = new ColumbusGuy[20][20];
    NormalRock[][] rocks = new NormalRock[20][20];
    ArrayList<BabyOnBoard> babies = new ArrayList<>();
    int xB = 0;
    int yB = 0;
    int tR = 0;
    int tC = 0;
    int counter = 0;
    int ahhstinky = 125;
    int imageX = 0;
    int imageY = 0;
    int xLength = 150;
    int yLength = 100;
    boolean pressed = false;
    boolean sad = false;
    
    private static BufferedImage ammo, piece;

    static {
        try {
            ammo = ImageIO.read(new File("theAMMUNITION.png"));
            piece = ImageIO.read(new File("deepfried_1568835288138.png"));
        } catch (IOException ex) {
            Logger.getLogger(RPGButOk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RPGButOk() throws IOException {
        for (int r = 0; r < finalmap.length; r++) {
            for (int c = 0; c < finalmap[r].length; c++) {
                tR = r % 5;
                tC = c % 5;
                if (Math.random() < 1.0 / 3) {
                    rocks[r][c] = new NormalRock(tR * 160 + (int) (Math.random() * 130), tC * 160 + (int) (Math.random() * 130), true);
                } else {
                    rocks[r][c] = new NormalRock(tR * 160 + (int) (Math.random() * 130), tC * 160 + (int) (Math.random() * 130), false);
                }
                finalmap[r][c] = new ColumbusGuy(tR * 160, tC * 160, (int) (Math.random() * 2) == 0);
            }

        }
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                RPGButOk.this.keyPressed(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressed(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released(e.getPoint());
            }
        });
    }

    /*public RPGButOk() throws FileNotFoundException {
       Scanner scnr = new Scanner(new File("src/rpgbutok/bruh.txt"));
       // = scnr.nextLine();
        //System.out.println(bruh);
    }*/
    public static void main(String[] args) throws IOException, FileNotFoundException {
        //Scanner scnr = new Scanner(new File("src/rpgbutok/bruh.txt"));

        WindowRPG AHHHHHH = new WindowRPG();

    }

    @Override
    public void paint(Graphics g) {

        checkArea();
        try {
            rockCheck();
        } catch (IOException ex) {
            Logger.getLogger(RPGButOk.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            bulletCheck();
        } catch (IOException ex) {
            Logger.getLogger(RPGButOk.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* try {
            rockCheck();
        } catch (IOException ex) {
            Logger.getLogger(RPGButOk.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //     if(imageX > 800);
        for (int x = 0; x < 5; x++) {
            // System.out.println(finalmap[x].length);
            for (int y = 0; y < 5; y++) {
                //System.out.println("bruh");

                finalmap[5 * xB + x][5 * yB + y].paint(g);
                if (rocks[5 * xB + x][5 * yB + y].hmm()) {

                    rocks[5 * xB + x][5 * yB + y].paint(g);
                }
                // finalmap[x][y].paint(g);
            }
        }

        //g.setColor(getBackground());
        //g.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
        // g.fillRect(0, 0, getWidth(), getHeight());
        //  String vro = scnr.next();
        //   int tempX = imageX
        //try {
        //   vro = new BabyOnBoard(imageX + 50, imageY + 25);
        //} catch (IOException ex) {
        //   Logger.getLogger(RPGButOk.class.getName()).log(Level.SEVERE, null, ex);
        //}
        g.drawImage(piece, imageX, imageY, xLength, yLength, null);
        for (int z = 0; z < babies.size(); z++) {
            BabyOnBoard baby = babies.get(z);
            baby.moveBaby();
            if (baby.getY() < -baby.getSize() || baby.getY() > 800
                    || baby.getX() < -baby.getSize() || baby.getX() > 800) {
                // kill the baby
                babies.remove(z);
            }
            baby.paint(g);
        }

        if (sad && counter % 20 == 0) {
            babies.add(new BabyOnBoard(imageX + 50, imageY + 25));
            //babies.add(new BabyOnBoard())
            //window.drawImage(ammo, imageX + 35, imageY + 20, 50, 50, null);
            //sad = false;
            counter = 0;
        }
        counter++;
        g.setFont(new Font("Arial", Font.BOLD, 25));

        /**
         * if (scnr.hasNextLine()) { if (counter % 2 == 1) { //String succ =
         * scnr.nextLine(); window.setColor(Color.black);
         * //System.out.println(succ);
         *
         * window.drawString(succ, 100, ahhstinky); ahhstinky += 25; try {
         * Thread.sleep(0); } catch (InterruptedException ex) {
         * Logger.getLogger(RPGButOk.class.getName()).log(Level.SEVERE, null,
         * ex); } } * }*
         */

        /*  while(scnr.hasNextLine()) {
        
            String vro = scnr.nextLine();
            window.setFont(new Font("Comic Sans MS", 10, 25));
            window.drawString(vro, 110, 125);
        }*/
 /* if (counter % 2 == 0) {
            window.setColor(getBackground());
            window.fillRect(50, 50, 500, 100);
            window.setColor(Color.black);
            window.setFont(new Font("Comic Sans MS", 10, 25));
            window.drawString("00                    00", 100, 100);
            window.drawString("  0000000000",110,125);
            counter--;
        } else {
            window.setColor(getBackground());
            window.fillRect(50, 50, 500, 100);
            window.setColor(Color.black);
            window.setFont(new Font("Comic Sans MS", 10, 25));
            window.drawString("00                    00", 100, 100);
            counter++;
        }
         */
        try {
            //window.drawRect(0, 0, 10, 100);
            //window.drawString("bruh", 2, 100);

            Thread.sleep(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(RPGButOk.class.getName()).log(Level.SEVERE, null, ex);
        }

        drawButton(g);
        repaint();
    }

    public void wipeBabies() {
        babies.clear();
    }

    public void bulletCheck() throws IOException {

        for (int x = 0; x < babies.size(); x++) {
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    Rectangle2D.Double bruh = new Rectangle2D.Double(babies.get(x).getX(), babies.get(x).getY(), babies.get(x).getSize(), babies.get(x).getSize());
                    if (rocks[5 * xB + r][5 * yB + c].intersects(bruh)) {
                        //System.out.println("life is good");
                        rocks[5 * xB + r][5 * yB + c].LIVE();
                    }
                }

            }

        }
    }

    public void drawButton(Graphics window) {
        if (pressed == false) {
            window.setColor(Color.black);
            window.fillRect(1200, 400, 200, 50);
        } else {

            window.setColor(Color.gray);
            window.fillRect(1200, 400, 200, 50);
        }
        //if(e.getX() > 1200 && e.getX() < 1400 && )

    }

    public void rockCheck() throws IOException {

        Rectangle2D.Double bruh = new Rectangle2D.Double(imageX, imageY, xLength, yLength);
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {

                if (rocks[5 * xB + r][5 * yB + c].intersects(bruh)) {

                    rocks[5 * xB + r][5 * yB + c].DIE();
                }
            }

        }

    }

    public void checkArea() {
        if (imageX < 0) {
            if (xB != 0) {
                imageX = 800 - xLength;
                xB--;
                wipeBabies();
            } else {

                imageX = 0;
            }

        }
        if (imageX + xLength > 800) {
            if (xB != 3) {
                imageX = 0;
                xB++;
                wipeBabies();
            } else {

                imageX = 800 - xLength;
            }

        }
        if (imageY < 0) {
            if (yB != 0) {
                imageY = 800 - yLength;
                yB--;
                wipeBabies();
            } else {

                imageY = 0;
            }

        }
        if (imageY + yLength > 800) {
            if (yB != 3) {
                imageY = 0;
                yB++;
                wipeBabies();
            } else {

                imageY = 800 - yLength;
            }
        }

    }

    public void pressed(Point e) {
        System.out.println("bad");
        if ((e.x > 1200 && e.x < 1600) && (e.y < 600 && e.y > 400)) {
            pressed = !pressed;
        }

    }

    public void released(Point e) {
        if ((e.x > 1200 && e.x < 1600) && (e.y < 600 && e.y > 400)) {
            pressed = !pressed;
        }

    }

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                imageX += 10;
                repaint();
                break;
            case KeyEvent.VK_DOWN:
                imageY += 10;
                repaint();
                break;
            case KeyEvent.VK_LEFT:
                imageX -= 10;
                repaint();
                break;
            case KeyEvent.VK_UP:
                imageY -= 10;
                repaint();
                break;
            case KeyEvent.VK_SPACE:
                sad = !sad;
                repaint();
                break;
            default:
                break;
        }

    }

}
