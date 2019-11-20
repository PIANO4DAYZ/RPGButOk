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

public final class RPGButOk extends JPanel implements Runnable {

    //BabyOnBoard vro = null;
    ColumbusGuy[][][] finalmap = new ColumbusGuy[5][20][20];

    NormalRock[][][] rocks = new NormalRock[5][20][20];
    ArrayList<BabyOnBoard> babies = new ArrayList<>();
    ArrayList<Vore1255> vores = new ArrayList<>();
    int frameShift;
    int xB = 0;
    int yB = 0;
    int tR = 0;
    int tC = 0;
    int level = 0;
    int counter = 0;
    int ahhstinky = 125;
    int imageX = 0;
    int imageY = 0;
    int xLength = 150;
    int yLength = 100;
    BattleScreen battle;
    boolean touchRock;
    boolean space;
    boolean bruh;
    boolean q = false;
    boolean pressed = false;
    boolean sad = false, isRight = true;
    public ArrayList<String> quotes = new ArrayList<>();
    private static BufferedImage ammo, piece, god, pieceTwo;

    static {
        try {
            god = ImageIO.read(new File("oh no.png"));
            ammo = ImageIO.read(new File("theAMMUNITION.png"));
            piece = ImageIO.read(new File("YES.jpg"));
            pieceTwo = ImageIO.read(new File("chomp.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(RPGButOk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RPGButOk() throws IOException {
        quotes.add("oh no bro");
        quotes.add("coochie");
        quotes.add("hoho your mom is gOYYYYYYY");
        quotes.add("vro not my habanero deviled egos");
        quotes.add("did you know that 98 percent of\n"
                + "virgins within the american states\n"
                + "actually originate frtom the small\n"
                + "southern country of the middle east,\n"
                + "when the arabians took over lord gigalent.\n"
                + "He was a evil and dangerous man, y el\n"
                + "era muyyyyyyyyyyyyyyyyyyyy disordenado.\n"
                + "Pero, el es muy guapo, y es un aguacate.");
            touchRock = false;
        level = 0;
        battle = new BattleScreen(0, "bad");
        space = false;
        bruh = false;
        for (int a = 0; a < finalmap.length; a++) {
            int stairX = (int) (Math.random() * 20);
            int stairY = (int) (Math.random() * 20);
            for (int r = 0; r < finalmap[a].length; r++) {
                for (int c = 0; c < finalmap[a][r].length; c++) {
                    tR = r % 5;
                    tC = c % 5;
                    if (Math.random() < 1.0 / 3) {
                        rocks[a][r][c] = new NormalRock(tR * 160 + (int) (Math.random() * 130), 
                                tC * 160 + (int) (Math.random() * 130), true, quotes.get((int)(Math.random() * quotes.size())));
                    } else {
                        rocks[a][r][c] = new NormalRock(tR * 160 + (int) (Math.random() * 130), 
                                tC * 160 + (int) (Math.random() * 130), false, quotes.get((int)(Math.random() * quotes.size())));
                    }
                    if ((r == stairX) && (c == stairY)) {

                        finalmap[a][r][c] = new ColumbusGuy(tR * 160, tC * 160, a, (int) (Math.random() * 2) == 0, true);

                    } else {
                        finalmap[a][r][c] = new ColumbusGuy(tR * 160, tC * 160, a, (int) (Math.random() * 2) == 0, false);
                    }
                }

            }
        }
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                RPGButOk.this.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                RPGButOk.this.keyReleased(e);
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
            rockCheckAndChange();
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

                finalmap[level][5 * xB + x][5 * yB + y].paint(g);
                if (rocks[level][5 * xB + x][5 * yB + y].hmm()) {

                    rocks[level][5 * xB + x][5 * yB + y].paint(g);
                }
                for (int vore = 0; vore < vores.size(); vore++) {

                    vores.get(vore).paint(g);
                }
                // finalmap[x][y].paint(g);
            }
        }
        try {
            if(rockCheck()){
                battle.paint(g);
            }
        } catch (IOException ex) {
            Logger.getLogger(RPGButOk.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (q) {
            exitCheck();
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
        if (frameShift < 15) {
            if (isRight) {
                g.drawImage(piece, imageX, imageY, xLength, yLength, null);
            } else {
                g.drawImage(piece, imageX + xLength, imageY, -xLength, yLength, null);
            }
        } else {
            if (isRight && space) {
                g.drawImage(pieceTwo, imageX + xLength, imageY, -xLength, yLength, null);
            } else if (space) {
                g.drawImage(pieceTwo, imageX, imageY, xLength, yLength, null);
            } else if (isRight) {
                g.drawImage(piece, imageX, imageY, xLength, yLength, null);

            } else {
                g.drawImage(piece, imageX + xLength, imageY, -xLength, yLength, null);

            }
        }
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
            BabyOnBoard bb = new BabyOnBoard(imageX + 50, imageY + 25);
            if (isRight) {
                bb.setDX(Math.abs(bb.getDX()));
            } else {
                bb.setDX(-Math.abs(bb.getDX()));
            }
            babies.add(bb);
            //babies.add(new BabyOnBoard())
            //window.drawImage(ammo, imageX + 35, imageY + 20, 50, 50, null);
            //sad = false;
            counter = 0;
        }
        counter++;
        // g.setFont(new Font("Arial", Font.BOLD, 25));
        if (bruh) {
            vores.add(new Vore1255((int) (Math.random() * 800), (int) (Math.random() * 800), (int) (Math.random() * 100)));

            //g.drawImage(god, (int) (Math.random() * 800),  (int) (Math.random() * 800),  (int) (Math.random() * 100),  (int) (Math.random() * 100), null);
            bruh = false;
        }

        drawButton(g);

    }

    public void wipeBabies() {
        babies.clear();
    }

    public void exitCheck() {
        Rectangle2D.Double bruh = new Rectangle2D.Double(imageX, imageY, xLength, yLength);

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                ColumbusGuy temp = finalmap[level][5 * xB + r][5 * yB + c];

                if (temp.getExit() && temp.intersects(bruh)) {
                    if (level < 4) {
                        finalmap[level + 1][5 * xB + r][5 * yB + c].setExit();

                        //System.out.println("gucci");
                    }
                    if(level < 4){
                    level++;
                    imageX = temp.getX() + (160 - xLength) / 2;

                    imageY = temp.getY() + (160 - yLength) / 2;
                    System.out.println("exit found");
                    }
      return;             
                }
            }
        }

    }

    public void bulletCheck() throws IOException {

        for (int x = 0; x < babies.size(); x++) {
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    Rectangle2D.Double bruh = new Rectangle2D.Double(babies.get(x).getX(), babies.get(x).getY(), babies.get(x).getSize(), babies.get(x).getSize());
                    if (rocks[level][5 * xB + r][5 * yB + c].intersects(bruh)) {
                        //System.out.println("life is good");
                        rocks[level][5 * xB + r][5 * yB + c].live();
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
    public boolean rockCheck() throws IOException {
    
    Rectangle2D.Double bruh = new Rectangle2D.Double(imageX, imageY, xLength, yLength);

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {

                if (rocks[level][5 * xB + r][5 * yB + c].intersects(bruh) && rocks[level][5 * xB + r][5 * yB + c].hmm()) {
                      battle = new BattleScreen(0, rocks[level][5 * xB + r][5 * yB + c].getQuote());
                    //System.out.println(rocks[level][5 * xB + r][5 * yB + c].getX() + "," + rocks[level][5 * xB + r][5 * yB + c].getY());
                    return true;
                }
            }

        }
        return false;
    }
    public void rockCheckAndChange() throws IOException {

        Rectangle2D.Double vro = new Rectangle2D.Double(imageX, imageY, xLength, yLength);

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {

                if (rocks[level][5 * xB + r][5 * yB + c].intersects(vro) && rocks[level][5 * xB + r][5 * yB + c].hmm()) {
                 
                    rocks[level][5 * xB + r][5 * yB + c].die();
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
        // System.out.println("bad " + e.x + ", " + e.y);
        if ((e.x > 1200 && e.x < 1400) && (e.y < 450 && e.y > 400)) {
            pressed = true;
            bruh = true;
        }

    }

    public void released(Point e) {
        pressed = false;
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            q = false;
            // System.out.println("stinky");
        }
    }

    public void keyPressed(KeyEvent e) {
        // System.out.println(e.getKeyCode());

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if (level < 4) {
                    level++;
                }
                break;
            case KeyEvent.VK_S:
                if (level > 0) {
                    level--;
                }
                break;
            case KeyEvent.VK_RIGHT:
                isRight = true;
                imageX += 10;
                break;
            case KeyEvent.VK_DOWN:
                imageY += 10;
                break;
            case KeyEvent.VK_LEFT:
                isRight = false;
                imageX -= 10;
                break;
            case KeyEvent.VK_UP:
                imageY -= 10;
                break;
            case KeyEvent.VK_SPACE:
                sad = !sad;
                space = !space;
                break;
            case KeyEvent.VK_Q:
                q = true;

                break;
        }

    }

    @Override
    public void run() {
        for (;/* (;-;) */;) {
            repaint();
            // cgiludtsgkuihfgdmudr :)
            frameShift++;
            frameShift %= 30;

            try {
                Thread.sleep(16);
            } catch (InterruptedException ex) {
                Logger.getLogger(RPGButOk.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
