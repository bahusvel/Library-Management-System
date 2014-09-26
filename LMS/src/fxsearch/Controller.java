package fxsearch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import managers.search.entities.BookSearch;
import persistance.Book;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller {
    BookSearch bookSearch;
    private boolean toggle = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> resultsList;

    @FXML
    private ListView<String> facetsList;

    @FXML
    private ComboBox<String> searchField;

    @FXML
    void searchPressed(ActionEvent event) {
        bookSearch.setInput(searchField.getEditor().getText());
        bookSearch.fuzzySearch("title");
        resultsList.getItems().clear();
        List<Book> resultList = bookSearch.getResults().getResultList();
        resultList.forEach(b -> resultsList.getItems().add(b.toString()));

    }

    @FXML
    void initialize() {
        bookSearch = new BookSearch("");


        assert resultsList != null : "fx:id=\"resultsList\" was not injected: check your FXML file 'search.fxml'.";
        assert facetsList != null : "fx:id=\"facetsList\" was not injected: check your FXML file 'search.fxml'.";


        searchField.valueProperty().addListener((observable, oldValue, newValue) -> {
            bookSearch.setInput(searchField.getEditor().getText());
            if (toggle) searchField.getItems().clear();
            toggle = !toggle;
            bookSearch.getSuggestions().forEach(searchField.getItems()::add);
            searchField.show();
        });
    }
}

