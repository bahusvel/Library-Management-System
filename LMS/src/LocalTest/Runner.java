package LocalTest;

import javax.swing.*;
import java.awt.*;

/**
 * Created by denislavrov on 9/9/14.
 */
public class Runner {
    public static void main(String[] args) {
        // Runnable() replaced with lambda
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Search Tester");
            BetterSearch mw = new BetterSearch();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(mw.getRootPanel());
            frame.setVisible(true);
            frame.pack();
            frame.setLocationRelativeTo(null);

        });
    }
}
