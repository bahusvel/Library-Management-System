package test.fxsearch;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import managers.search.SearchResults;
import managers.search.entities.BookSearch;
import org.controlsfx.control.PopOver.ArrowLocation;
import persistance.Book;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    @FXML
    private TreeView<String> facetTree;

    private SuggestionsPopOver suggestions;

    ListView<String> popoverContent = new ListView<>(FXCollections.observableArrayList());

    @FXML
    void searchPressed(ActionEvent event) {
        bookSearch.setInput(searchField.getText());
        bookSearch.dynamicSearch(5,"title");
        updateResults();
        updateFacetTree();

    }

    private void updateResults(){
        searchResults = bookSearch.getResults();
        List<Book> resultList = searchResults.getResultList();
        resultsList.getItems().clear();
        resultList.forEach(b -> resultsList.getItems().add(b.toString()));
    }



    private void updateFacetTree(){
        facetTree.getRoot().getChildren().clear();
        searchResults.getFacetMap().keySet().forEach(k -> {
            TreeItem<String> fCat = new TreeItem<>(k);
            fCat.setExpanded(true);
            fCat.getChildren().addAll(searchResults.getFacetMap().get(k).stream().map(v -> new TreeItem<>(v.getValue())).collect(Collectors.toList()));
            facetTree.getRoot().getChildren().add(fCat);
        });

    }

    @FXML
    void facetReset(ActionEvent event) {
        bookSearch.resetFacets();
        updateFacetTree();
        updateResults();
    }

    @FXML
    void fireSuggest(KeyEvent event) {
        bookSearch.setInput(searchField.getText());
        popoverContent.getItems().clear();
        popoverContent.getItems().addAll(bookSearch.getSuggestions());

        suggestions.show(searchField);
    }

    private void initFacetTree(){
        facetTree.setRoot(new TreeItem<>());
        facetTree.setShowRoot(false);
        facetTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(!searchResults.getFacetMap().containsKey(newValue.getValue())) {
                bookSearch.resetFacets();
                bookSearch.selectFacet(newValue.getParent().getValue(), newValue.getValue());
                updateResults();
            }
        });
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
        popoverContent.setMaxHeight(150);
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
        initFacetTree();
        createPopOver();
    }
}

