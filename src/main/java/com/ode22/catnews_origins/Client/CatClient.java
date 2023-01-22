package com.ode22.catnews_origins.Client;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ode22.catnews_origins.Dto.RandomCat;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Client containing all logic for communication with thecatapi.com
 * Documentation at https://developers.thecatapi.com/
 * Extends the Service<> Class from the javafx.concurrent.* package handle GUI updates with multiple threads.
 */
public class CatClient extends Service<RandomCat>
{
    final String host = "https://api.thecatapi.com/v1/images/search";
    ObjectMapper mapper = new ObjectMapper();
    RandomCat randomCat = new RandomCat();

    public CatClient() {

    }

    /**
     * Sets the image of location to a random cat picture after the createTask method has completed and its thread has died.
     * @param location the ImageView location where the picture is supposed to be placed
     */
    public CatClient(ImageView location) {
       setOnSucceeded(event -> location.setImage(new Image(((RandomCat) event.getSource().getValue()).getUrl())));
       setOnFailed(event -> {
           File file = new File("src/main/resources/com/ode22/catnews_origins/503.jpg");
           location.setImage(new Image(file.toURI().toString()));
       });
    }

    /**
     * Requests a random cat image (with no additional parameters) from the cat api.
     * @return an instance of RandomCat containing the address of the image, image ID and width/height.
     * @throws IOException
     */
    public RandomCat getRandomCat() throws IOException {

        URL url = new URL(host);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        InputStream stream =  con.getInputStream();

        // Create byte array containing input stream.
        byte[] byteResponse = stream.readAllBytes();

        // Convert byte array to string.
        String charResponse = new String(byteResponse, StandardCharsets.UTF_8);

        // Convert string from json format to object. TypeReference is called because thecatapi.com returns array of size 1.
        randomCat = mapper.readValue(charResponse, new TypeReference<List<RandomCat>>() {}).get(0);

        con.disconnect();
        return randomCat;
    }


    /**
     * Allows the getRandomCat() method to be called in a separate thread.
     * @return returns a randomCat object.
     */
    @Override
    protected Task createTask() {
        return new Task<RandomCat>() {
            @Override
            protected RandomCat call() throws IOException {
                return getRandomCat();
            }
        };
    }
}
