package com.ode22.catnews_origins.Client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ode22.catnews_origins.Dto.ArticleHeaders;
import com.ode22.catnews_origins.Dto.RandomCat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

/**
 * Client containing all the logic for communication with the APA api.
 * Documentation at https://api.ots.at/doku/
 */
public class ApaClient {

    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);


    final String HOST = "https://www.ots.at/api/";


    /**
     * Makes a request from the APA api with the given data
     * @param search Key Keywords which should be searched for (TODO: can't handle whitespaces)
     * @param count The number of elements that should be requested
     * @return the ArticleHeaders-object containing the received response from the APA api
     * @throws IOException
     * @throws ProtocolException
     */
    public ArticleHeaders getArticleHeaders(String search, int count) throws IOException, ProtocolException  {

        URL url = null;

        url = new URL(HOST + "liste/?app=" + getKey() + "&query=" + search + "&anz=" + count );
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        InputStream stream = null;
        stream = con.getInputStream();

        // Create byte array
        byte[] byteResponse = stream.readAllBytes();

        // Converting the byte array to a string
        String charResponse = new String(byteResponse, StandardCharsets.UTF_8);

        // Converting the string into an object
        var articleHeaders = mapper.readValue(charResponse, ArticleHeaders.class);


        con.disconnect();
        return articleHeaders;
    }

    /**
     * Fetches the private api_key for connecting to APA from a file located in resources/com/ode22/catnews_origins/,
     * which is intentionally not checked into the repository.
     *
     * @return the api_key for the APA api as a String
     */
    private String getKey() {
        try (InputStream input = new FileInputStream("src/main/resources/com/ode22/catnews_origins/config.properties")) {

            Properties prop = new Properties();

            // load the config file
            prop.load(input);

            // fetch the property and return it
            return prop.getProperty("apa.key");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
