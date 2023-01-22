package com.ode22.catnews_origins;

import com.ode22.catnews_origins.Client.ApaClient;
import com.ode22.catnews_origins.Client.CatClient;
import com.ode22.catnews_origins.Dto.Article;
import com.ode22.catnews_origins.Dto.ArticleHeaders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CatGuiController implements Initializable {

    ApaClient apaClient = new ApaClient();
    ArticleHeaders articleHeaders = new ArticleHeaders();
    FileHandler fileHandler = new FileHandler();
    @FXML
    private Button btnOpenTodaysFile;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private DatePicker datePickerEndDate;

    @FXML
    private DatePicker datePickerStartDate;

    @FXML
    private HBox hboxMain;

    @FXML
    private ImageView imageCat;

    @FXML
    private Label labelCat;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelDate1;

    @FXML
    private Label labelDateTo;

    @FXML
    private Label labelMaxNumber;

    @FXML
    private Label labelSelcArticles;

    @FXML
    private Label labelTitle;

    @FXML
    private ListView<String> listviewAllArticles;

    ObservableList<String> articleHeaderList = FXCollections.observableArrayList();

    @FXML
    private ListView<String> listviewSavedItems;

    ObservableList<String> savedItemsList = FXCollections.observableArrayList();

    @FXML
    private TextField txtMaxArticles;

    @FXML
    private TextField txtTitel;

    @FXML
    private VBox vboxLeft;

    @FXML
    
    private VBox vboxRight;

    @FXML
    void onOpenTodaysFile(ActionEvent event) {
        System.out.println("Open pressed");
        try {
            fileHandler.openDailyFile();
        } catch (IOException e) {

            //In case of an error, we open an alert-window to inform the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "The file could not be opened ", ButtonType.OK);
            alert.showAndWait();
        }

    }
    @FXML
    void onSearch(ActionEvent event){
        System.out.println("Search pressed");
        articleHeaderList.clear();
        listviewAllArticles.setItems(articleHeaderList);
        try {
            articleHeaders = apaClient.getArticleHeaders(txtTitel.getText(), 10);
            articleHeaders.getErgebnisse().forEach(articleHeader -> articleHeaderList.add(articleHeader.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void onSave(ActionEvent event) throws IOException {
        System.out.println("Save pressed");
        //Getting the index of the current selected article
        var selectedIndex = listviewAllArticles.getSelectionModel().getSelectedIndex();

        //Checks that an article has been selected (otherwise returns -1
        if(selectedIndex == -1) {

            //In case no article was selected, we open an alert-window to inform the user
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No article has been selected", ButtonType.OK);
            alert.showAndWait();

        } else {
            //Gets the article 'schluessel' from the article-headers list and fetches the article from the api
            Article article = apaClient.getArticle(articleHeaders.getErgebnisse().get(selectedIndex).getSchluessel());

            //Saves the article in today's file
            fileHandler.saveArticle(article);

            //Saves the article header in the savedItems List
            savedItemsList.add(article.getTitel());

            //Displays the saved articles
            listviewSavedItems.setItems(savedItemsList);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //load cat image on startup
        loadRandomCatImage(imageCat);
    }

    /**
     * Loads a random cat image from thecatapi.com into a ImageView node.
     * @param location the ImageView node into which the image is to be loaded.
     */
    public void loadRandomCatImage(ImageView location) {
        CatClient catClient = new CatClient();
        try {
            location.setImage(new Image(catClient.getRandomCat().getUrl()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}