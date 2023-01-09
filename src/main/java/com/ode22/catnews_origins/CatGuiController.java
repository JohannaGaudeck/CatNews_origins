package com.ode22.catnews_origins;

import com.ode22.catnews_origins.Client.CatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CatGuiController implements Initializable {
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
    private Label lableCat;

    @FXML
    private Label lableDate;

    @FXML
    private Label lableDate1;

    @FXML
    private Label lableDateTo;

    @FXML
    private Label lableMaxNumber;

    @FXML
    private Label lableSelcArticles;

    @FXML
    private Label lableTitle;

    @FXML
    private ListView<?> listviewAllArticles;

    @FXML
    private ListView<?> listviewSavedItems;

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

    }
    @FXML
    void onSearch(ActionEvent event){
        System.out.println("Search pressed");
    }

    @FXML
    void onSave(ActionEvent event){
        System.out.println("Save pressed");
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