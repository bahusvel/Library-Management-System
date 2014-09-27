package fxsearch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import managers.search.SearchResults;
import managers.search.entities.BookSearch;
import persistance.Book;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller {
    BookSearch bookSearch;
    SearchResults<Book> searchResults;
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
        bookSearch.dynamicSearch(5,"title");
        updateResults();
        updateFacets();

    }

    private void updateResults(){
        searchResults = bookSearch.getResults();
        List<Book> resultList = searchResults.getResultList();
        resultsList.getItems().clear();
        resultList.forEach(b -> resultsList.getItems().add(b.toString()));
    }

    private void updateFacets(){
        facetsList.getItems().clear();
        searchResults
                .getFacetMap()
                .get("categoryFacetingRequest")
                .forEach(facet -> facetsList.getItems().add(facet.getValue() + " (" + facet.getCount() + ")"));
    }

    @FXML
    void facetReset(ActionEvent event) {
        bookSearch.resetFacets();
        updateFacets();
        updateResults();
    }

    @FXML
    void initialize() {
        bookSearch = new BookSearch("");

        facetsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            bookSearch.resetFacets();
            bookSearch.selectFacet("categoryFacetingRequest", facetsList.getSelectionModel().getSelectedIndex());
            updateResults();
        });

        searchField.valueProperty().addListener((observable, oldValue, newValue) -> {
            bookSearch.setInput(searchField.getEditor().getText());
            if (toggle) searchField.getItems().clear();
            toggle = !toggle;
            bookSearch.getSuggestions().forEach(searchField.getItems()::add);
            searchField.show();
        });
    }
}

