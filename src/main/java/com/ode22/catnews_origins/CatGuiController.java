package com.ode22.catnews_origins;

import com.ode22.catnews_origins.Client.ApaClient;
import com.ode22.catnews_origins.Client.CatClient;
import com.ode22.catnews_origins.Dto.Article;
import com.ode22.catnews_origins.Dto.ArticleHeaders;
import com.ode22.catnews_origins.Handler.DateHandler;
import com.ode22.catnews_origins.Handler.FileHandler;
import com.ode22.catnews_origins.Handler.ProblemHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    DateHandler datehandler = new DateHandler();
    ProblemHandler problemHandler = new ProblemHandler();

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
        try {
            fileHandler.openDailyFile();
        } catch (IOException e) {
            //In case of an error, we open an alert-window to inform the user
            problemHandler.info("Unable to open file");
            throw new RuntimeException(e);
        }
        //refreshes the random cat picture with a new one
        loadRandomCatImage(imageCat);

    }
    @FXML
    void onSearch(ActionEvent event){
        articleHeaderList.clear();
        listviewAllArticles.setItems(articleHeaderList);
        try {
            if(txtTitel.getText().isEmpty()){
                //In case no title was selected, we open an alert-window to inform the user
                problemHandler.info("No title selected");
            }
           else if(txtMaxArticles.getText().isEmpty() ){
                if (datePickerStartDate.getValue() == null && datePickerEndDate.getValue() == null){
                    articleHeaders = apaClient.getArticleHeaders(txtTitel.getText(), 10);
                } else if (datePickerStartDate.getValue() != null && datePickerEndDate.getValue() != null){
                    long startDate = datehandler.get_unix_timestamp(String.valueOf(datePickerStartDate.getValue()));
                    long endDate = datehandler.get_unix_timestamp(String.valueOf(datePickerEndDate.getValue()));
                    articleHeaders = apaClient.getArticleHeaders(txtTitel.getText(), 10, startDate,endDate);
                } else {
                    //In case only one date was selected, we open an alert-window to inform the user
                    problemHandler.info("Start and end date must be set");
                }
            }
            else{
                if (datePickerStartDate.getValue() == null && datePickerEndDate.getValue() == null){
                    articleHeaders = apaClient.getArticleHeaders(txtTitel.getText(),  Integer.parseInt(txtMaxArticles.getText()));
                }else if (datePickerStartDate.getValue() != null && datePickerEndDate.getValue() != null){
                    long startDate = datehandler.get_unix_timestamp(String.valueOf(datePickerStartDate.getValue()));
                    long endDate = datehandler.get_unix_timestamp(String.valueOf(datePickerEndDate.getValue()));
                    articleHeaders = apaClient.getArticleHeaders(txtTitel.getText(),  Integer.parseInt(txtMaxArticles.getText()), startDate, endDate);
                }else {
                    //In case only one date was selected, we open an alert-window to inform the user
                    problemHandler.info("Start and end date must be set");
                }

            }
            articleHeaders.getErgebnisse().forEach(articleHeader -> articleHeaderList.add(articleHeader.toString()));
        } catch (IOException e) {
            problemHandler.error("A problem occured while fetching articles from APA");
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            problemHandler.error("Maximum number of articles must contain numerical characters!");
        }
        //refreshes the random cat picture with a new one
        loadRandomCatImage(imageCat);

    }

    @FXML
    void onSave(ActionEvent event) {
        //Getting the index of the current selected article
        var selectedIndex = listviewAllArticles.getSelectionModel().getSelectedIndex();

        //Checks that an article has been selected (otherwise returns -1
        if(selectedIndex == -1) {

            //In case no article was selected, we open an alert-window to inform the user
            problemHandler.info("No article has been selected");

        } else {
            try {
                //Gets the article 'schluessel' from the article-headers list and fetches the article from the api
                Article article = apaClient.getArticle(articleHeaders.getErgebnisse().get(selectedIndex).getSchluessel());

                //Saves the article in today's file
                fileHandler.saveArticle(article);

                //Saves the article header in the savedItems List
                savedItemsList.add(article.getTitel());

                //Displays the saved articles
                listviewSavedItems.setItems(savedItemsList);
            } catch (IOException e) {
                problemHandler.error("An article related problem occured!");
            }

        }
        //refreshes the random cat picture with a new one
        loadRandomCatImage(imageCat);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //load cat image on startup
        loadRandomCatImage(imageCat);
    }

    /**
     * Starts a new thread that loads a random cat image from thecatapi.com into a ImageView node.
     * @param location the ImageView node into which the image is to be loaded.
     */
    public void loadRandomCatImage(ImageView location) {
        new CatClient(location).start();
    }


}