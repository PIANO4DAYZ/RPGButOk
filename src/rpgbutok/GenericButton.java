package rpgbutok;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class GenericButton {

    private Rectangle2D.Double hitbox;
    int l;
    int h;
    int x;
    int y;

    public GenericButton(int length, int height, int xok, int yellow) {
        hitbox = new Rectangle2D.Double(length, height, xok, yellow);
        l = length;
        h = height;
        x = xok;
        y = yellow;

    }

    public void paint(Graphics window) {
        window.drawRect(x, y, l, h);
    }
}
