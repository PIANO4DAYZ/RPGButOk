package rpgbutok;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * The main class
 *
 * @author Brighton Liu, Jed Wang
 */
public class WindowRPG {

    /**
     * The main method
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            RPGButOk rpgButOk = new RPGButOk();
            frame.add(rpgButOk);
            // frame.setSize(818, 840);
            frame.setUndecorated(true);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setTitle("jeff");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            rpgButOk.requestFocus();
            new Thread(rpgButOk).start();
        });
    }
}
