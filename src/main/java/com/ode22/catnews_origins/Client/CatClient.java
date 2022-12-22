package com.ode22.catnews_origins.Client;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ode22.catnews_origins.CatGuiController;
import com.ode22.catnews_origins.Dto.RandomCat;

/**
 * Client containing all logic for communication with thecatapi.com
 * Documentation at https://developers.thecatapi.com/
 */
public class CatClient
{
    final String host = "https://api.thecatapi.com/v1/images/search";
    ObjectMapper mapper = new ObjectMapper();
    RandomCat randomCat = new RandomCat();

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
}
