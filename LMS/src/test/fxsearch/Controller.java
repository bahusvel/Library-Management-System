package test.fxsearch;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import managers.search.SearchResults;
import managers.search.entities.BookSearch;
import org.controlsfx.control.PopOver.ArrowLocation;
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
    private TextField searchField;

    private SuggestionsPopOver suggestions;

    private static final String facet = "Pages";

    ListView<String> popoverContent = new ListView<>(FXCollections.observableArrayList());

    @FXML
    void searchPressed(ActionEvent event) {
        bookSearch.setInput(searchField.getText());
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
                .get(facet)
                .forEach(facet -> facetsList.getItems().add(facet.getValue() + " (" + facet.getCount() + ")"));
    }

    @FXML
    void facetReset(ActionEvent event) {
        bookSearch.resetFacets();
        updateFacets();
        updateResults();
    }

    @FXML
    void fireSuggest(KeyEvent event) {
        bookSearch.setInput(searchField.getText());
        popoverContent.getItems().clear();
        popoverContent.getItems().addAll(bookSearch.getSuggestions());

        suggestions.show(searchField);
    }


    public void createPopOver(){
        popoverContent.prefWidthProperty().bind(searchField.widthProperty());
        suggestions = new SuggestionsPopOver(popoverContent,0.5);
        Rectangle clip = new Rectangle();
        clip.setArcHeight(20.0);
        clip.setArcWidth(20.0);
        clip.xProperty().bind(popoverContent.translateXProperty().add(1));
        clip.yProperty().bind(popoverContent.translateYProperty().add(1));
        clip.widthProperty().bind(popoverContent.widthProperty().subtract(1));
        clip.heightProperty().bind(popoverContent.heightProperty().subtract(1));
        popoverContent.setClip(clip);
        popoverContent.setStyle("-fx-focus-color: transparent;");
        popoverContent.setMaxHeight(125);
        suggestions.setCornerRadius(10.0);
        suggestions.setArrowLocation(ArrowLocation.TOP_CENTER);
        suggestions.setAutoHide(true);

        popoverContent.setOnMouseClicked(event -> {
           searchField.setText(popoverContent.getSelectionModel().getSelectedItem());
        });
    }

    @FXML
    void initialize() {
        bookSearch = new BookSearch("");
        createPopOver();

        facetsList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            bookSearch.resetFacets();
            bookSearch.selectFacet(facet, facetsList.getSelectionModel().getSelectedIndex());
            updateResults();
        });
    }
}

