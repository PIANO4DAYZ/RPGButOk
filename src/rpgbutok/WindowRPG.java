package rpgbutok;

import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WindowRPG {

    public static void main(String[] args) throws IOException {
        new WindowRPG();
    }

    public WindowRPG() throws FileNotFoundException, IOException {
        //RPGButOk graph = new RPGButOk();

        JFrame frame = new JFrame();
        RPGButOk rpgButOk = new RPGButOk();
        frame.add(rpgButOk);
        frame.setSize(818, 840);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("jeff");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        rpgButOk.requestFocus();
        new Thread(rpgButOk).start();
    }

}
