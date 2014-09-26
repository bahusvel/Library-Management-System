package LocalTest;

import managers.search.SearchResults;
import managers.search.entities.BookSearch;
import persistance.Book;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Scanner;

/**
 * Created by denislavrov on 9/9/14.
 */
public class BetterSearch {
    private JPanel RootPanel;
    private JComboBox comboBox1;
    private JButton searchButton;
    private JTextPane textPane1;
    private JScrollPane scrollPane;
    private JList list1;
    private BookSearch bookSearch = new BookSearch("");
    private Scanner s = new Scanner(System.in);

    public BetterSearch() {
        comboBox1.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            @SuppressWarnings("unchecked")
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                final String input = ((JTextComponent)comboBox1.getEditor().getEditorComponent()).getText();
                bookSearch.setInput(input);
                comboBox1.removeAllItems();
                bookSearch.getSuggestions().forEach(comboBox1::addItem);
                ((JTextComponent)comboBox1.getEditor().getEditorComponent()).setText(input);
                comboBox1.setPopupVisible(true);
            }

        });
        searchButton.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            bookSearch.setInput(((JTextComponent) comboBox1.getEditor().getEditorComponent()).getText());
            bookSearch.dynamicSearch(5,"title");
            SearchResults<Book> bookResults = bookSearch.getResults();
            List<Book> books = bookResults.getResultList();
            books.forEach(book -> {
                sb.append(book);
                sb.append('\n');
            });
            textPane1.setText(sb.toString());
            bookResults.applyFacets(s.nextLine(), s.nextInt());
        });
    }

    public JPanel getRootPanel(){
        return RootPanel;
    }


}
