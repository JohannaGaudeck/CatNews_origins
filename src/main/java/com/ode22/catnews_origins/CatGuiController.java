package com.ode22.catnews_origins;

import com.ode22.catnews_origins.Client.ApaClient;
import com.ode22.catnews_origins.Client.CatClient;
import com.ode22.catnews_origins.Dto.Article;
import com.ode22.catnews_origins.Dto.ArticleHeader;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class CatGuiController implements Initializable {

    ApaClient apaClient = new ApaClient();
    ArticleHeaders articleHeaders = new ArticleHeaders();
    FileHandler fileHandler = new FileHandler();
    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private HBox hboxMain;

    @FXML
    private ImageView imageCat;

    @FXML
    private Label lableCat;

    @FXML
    private Label lableDate;

    @FXML
    private Label lableSelcArticles;

    @FXML
    private Label lableTitle;

    @FXML
    private ListView<String> listviewAllArticles;

    ObservableList<String> articleHeaderList = FXCollections.observableArrayList();

    @FXML
    private ListView<?> listviewSelectedArticles;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtTitel;

    @FXML
    private VBox vboxLeft;

    @FXML
    private VBox vboxRight;

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