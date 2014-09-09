package LocalTest;

import HibernateManager.HibernateManager;
import HibernateManager.SearchManager;
import Persistance.Book;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by denislavrov on 9/9/14.
 */
public class SearchTest {
    private JTextField textField1;
    private JButton searchButton;
    private JTextPane textPane1;
    private JPanel rootPane;
    private SearchManager sm = new SearchManager(HibernateManager.getSession());
    private ArrayList<String> strList = new ArrayList<>();

    public JPanel getRootPane(){
        return rootPane;
    }

    public SearchTest() {
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                final String input = textField1.getText();
                StringBuilder sb = new StringBuilder();
                for (Book b : sm.bookSuggestions(input,5)){
                    sb.append(b.getTitle());
                    sb.append("\n");
                }
                textPane1.setText(sb.toString());
            }
        });
    }
}
