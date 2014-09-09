package LocalTest;

import HibernateManager.HibernateManager;
import HibernateManager.SearchManager;
import Persistance.Book;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created by denislavrov on 9/9/14.
 */
public class BetterSearch {
    private JPanel RootPanel;
    private JComboBox comboBox1;
    private JButton searchButton;
    private JTextPane textPane1;
    private JScrollPane scrollPane;

    private SearchManager sm = new SearchManager(HibernateManager.getSession());

    public BetterSearch() {
        comboBox1.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            @SuppressWarnings("unchecked")
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                final String input = ((JTextComponent)comboBox1.getEditor().getEditorComponent()).getText();
                comboBox1.removeAllItems();
                sm.bookSuggestions(input,5).forEach(comboBox1::addItem);
                ((JTextComponent)comboBox1.getEditor().getEditorComponent()).setText(input);
                comboBox1.setPopupVisible(true);
            }

        });
        searchButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            List<Book> books = sm.dynamicFuzzy(Book.class, ((JTextComponent) comboBox1.getEditor().getEditorComponent()).getText(),5, "title");
            for (Book book : books){
                sb.append(book);
                sb.append('\n');
            }
            textPane1.setText(sb.toString());
        });
    }

    public JPanel getRootPanel(){
        return RootPanel;
    }


}
