package rpgbutok;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Random;

public class BattleScreen {
    private int type;
    private String text;
    private int layer;
    private int option;
    private int choice;
    private Random r;

    public BattleScreen(int x, String quote, int level, int o, int c) {
        text = quote;
        type = x;
        layer = level;
        choice = c;
        option = o;
        
        r = new Random();
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(Color.black);
        g2D.fillRect(0, 0, 800, 800);
        if (type == 0) {
            g2D.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_ROUND));

            for (int i = 0; i < 5; i++) {
                Path2D path = new Path2D.Double();
                path.moveTo(r.nextInt(800), r.nextInt(800));
                for (int j = 0; j < 3; j++) {
                    path.curveTo(r.nextInt(800), r.nextInt(800), r.nextInt(800), r.nextInt(800), r.nextInt(800), r.nextInt(800));
                }
                // path.closePath();
                g2D.setColor(new Color(r.nextInt(0xFFFFFF)));
                g2D.draw(path);
            }
            g2D.setColor(Color.white);
            g2D.drawRect(200, 300, 200, 50);
            g2D.drawRect(10, 500, 780, 100);
            g2D.drawString("hola", 500, 300);

            FontMetrics metrics = g2D.getFontMetrics();
            String[] temp = text.split("\n");
            int y = 325;
            for (String s : temp) {
                g2D.drawString(s, 300 - metrics.stringWidth(s) / 2, y);
                y += metrics.getHeight() + 5;

            }
            g2D.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
            System.out.println("layer: " + layer);
            switch(layer) {
                case 0:
                    g2D.drawString("Battle Options", 110, 560);
                    g2D.drawString("Items", 500, 560);
                    break;
                case 1:
                    switch (choice) {
                        case 0:
                            // g2D.drawString("hmm", 110, 560);
                            // g2D.drawString("thonk", 500, 560);
                            g2D.drawString("Hit it i guess", 110, 560);
                            g2D.drawString("SPECIAL MOVE", 500, 560);
                            break;
                        case 1:
                            g2D.drawString("mmm healing pot", 110, 560);
                            g2D.drawString("uhh food", 500, 560);
                            break;
                    }
                    break;
            }
            // g2D.drawString(text, 300 - width / 2, 325);
        }

    }

    public void updateLevel() {
        System.out.println("awesome");
        layer++;
    }
}
