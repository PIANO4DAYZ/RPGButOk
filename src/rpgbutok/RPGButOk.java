package rpgbutok;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class RPGButOk extends JPanel implements Runnable {

    //BabyOnBoard vro = null;
    private ColumbusGuy[][][] finalmap = new ColumbusGuy[5][20][20];

    private NormalRock[][][] rocks = new NormalRock[5][20][20];
    private ArrayList<BabyOnBoard> babies = new ArrayList<>();
    private ArrayList<Vore1255> vores = new ArrayList<>();
    private int counter30, xB = 0, yB = 0, level = 0,
            counter20, imageX = 0, imageY = 0, battleSelector, battleLayer,
            choice;
    private final int xLength = 150, yLength = 100;
    private BattleScreen battle;
    private boolean touchRock, space, bruh, q = false, pressed = false,
            sad = false, isRight = true, isBattle;
    private static BufferedImage piece, pieceTwo;

    private Random rand = new Random();

    static {
        try {
            piece = ImageIO.read(new File("src/resources/YES.jpg"));
            pieceTwo = ImageIO.read(new File("src/resources/chomp.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public RPGButOk() {
        touchRock = false;
        level = 0;
        battleSelector = 0;
        battleLayer = 0;
        choice = -1;
        battle = new BattleScreen(0, "bad", 0, 0, -1);
        space = false;
        bruh = false;
        isBattle = false;
        for (int a = 0; a < finalmap.length; a++) {
            int stairX = rand.nextInt(20);
            int stairY = rand.nextInt(20);
            for (int r = 0; r < finalmap[a].length; r++) {
                for (int c = 0; c < finalmap[a][r].length; c++) {
                    int tR = r % 5;
                    int tC = c % 5;
                    if (rand.nextInt(3) == 0) {
                        rocks[a][r][c] = new NormalRock(tR * 160 + rand.nextInt(130),
                                tC * 160 + rand.nextInt(130));
                    }
                    finalmap[a][r][c] = new ColumbusGuy(tR * 160, tC * 160,
                            a, rand.nextBoolean(), (r == stairX) && (c == stairY));
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

    @Override
    public void paint(Graphics g) {

        checkArea();
        rockCheckAndChange();
        bulletCheck();

        for (int x = 0; x < 5; x++) {
            // System.out.println(finalmap[x].length);
            for (int y = 0; y < 5; y++) {
                //System.out.println("bruh");
                finalmap[level][5 * xB + x][5 * yB + y].paint(g);
                NormalRock rock = rocks[level][5 * xB + x][5 * yB + y];
                if (rock != null) {
                    rock.paint(g);
                }

                for (int vore = 0; vore < vores.size(); vore++) {
                    vores.get(vore).paint(g);
                }
                // finalmap[x][y].paint(g);
            }
        }
        try {
            if (rockCheck()) {
                battle.paint(g);
                if (battleSelector == 0) {
                    g.drawOval(65, 540, 25, 25);
                } else if (battleSelector == 1) {
                    g.drawOval(455, 540, 25, 25);
                } else if (battleLayer >= 1) {
                    battle.updateLevel();
                    System.out.println("cool");
                } else if (battleLayer == 1) {
                    if (battleSelector == 0) {
                        g.drawOval(65, 540, 25, 25);
                    } else if (battleSelector == 1) {
                        g.drawOval(455, 540, 25, 25);
                    }
                }
                isBattle = true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (q) {
            exitCheck();
        }

        if (isBattle == false) {
            if (counter30 < 15) {
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

        if (sad && counter20 % 20 == 0) {
            BabyOnBoard bb = new BabyOnBoard(imageX + 50, imageY + 25);
            if (isRight) {
                bb.setDX(Math.abs(bb.getDX()));
            } else {
                bb.setDX(-Math.abs(bb.getDX()));
            }
            babies.add(bb);

            counter20 = 0;
        }
        counter20++;
        // g.setFont(new Font("Arial", Font.BOLD, 25));
        if (bruh) {
            vores.add(new Vore1255(rand.nextInt(800), rand.nextInt(800), rand.nextInt(100)));

            bruh = false;
        }

        drawButton(g);

    }

    public void wipeBabies() {
        babies.clear();
    }

    public void exitCheck() {
        Rectangle2D.Double bigBruh = new Rectangle2D.Double(imageX, imageY, xLength, yLength);

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                ColumbusGuy temp = finalmap[level][5 * xB + r][5 * yB + c];

                if (temp.getExit() && temp.intersects(bigBruh)) {
                    if (level < 4) {
                        finalmap[level + 1][5 * xB + r][5 * yB + c].setExit();

                        //System.out.println("gucci");
                    }
                    if (level < 4) {
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

    public void bulletCheck() {

        for (int x = 0; x < babies.size(); x++) {
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    Rectangle2D.Double bigBruh = new Rectangle2D.Double(
                            babies.get(x).getX(), babies.get(x).getY(),
                            babies.get(x).getSize(), babies.get(x).getSize());
                    if (rocks[level][5 * xB + r][5 * yB + c].intersects(bigBruh)) {
                        //System.out.println("life is good");
                        rocks[level][5 * xB + r][5 * yB + c].setIsAlive(true);
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

        Rectangle2D.Double bigBruh = new Rectangle2D.Double(imageX, imageY, xLength, yLength);

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                NormalRock rock = rocks[level][5 * xB + r][5 * yB + c];
                if (rock != null && rock.intersects(bigBruh)) {
                    battle = new BattleScreen(0, rock.getQuote(), battleLayer, battleSelector, choice);
                    // System.out.println(rock.getX() + "," + rock.getY());
                    return true;
                }
            }

        }
        return false;
    }

    public void rockCheckAndChange() {

        Rectangle2D.Double vro = new Rectangle2D.Double(imageX, imageY, xLength, yLength);

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                NormalRock rock = rocks[level][5 * xB + r][5 * yB + c];
                if (rock != null && rock.intersects(vro)) {
                    rock.setIsAlive(false);
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
        if (isBattle == false) {
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
        } else {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && battleSelector < 1) {

                battleSelector++;
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT && battleSelector > 0) {

                battleSelector--;
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER && battleSelector < 1) {
                battleLayer++;
                choice = 0;
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER && battleSelector > 0) {
                battleLayer++;
                choice = 1;
            }

        }
    }

    @Override
    public void run() {
        for (;/* (;-;) */;) {
            repaint();
            // cgiludtsgkuihfgdmudr :)
            counter30++;
            counter30 %= 30;

            // System.out.println(getWidth() + ", " + getHeight());
            try {
                Thread.sleep(16);
            } catch (InterruptedException ex) {
                Logger.getLogger(RPGButOk.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
