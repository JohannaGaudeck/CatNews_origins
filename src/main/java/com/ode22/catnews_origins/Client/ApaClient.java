package com.ode22.catnews_origins.Client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ode22.catnews_origins.Dto.Article;
import com.ode22.catnews_origins.Dto.ArticleHeaders;
import com.ode22.catnews_origins.Dto.Articles;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
     *
     * @param search Key Keywords which should be searched for
     * @param count The number of elements that should be requested
     * @return the ArticleHeaders-object containing the received response from the APA api
     * @throws IOException in case a problem with the connection to the api occurred
     */
    public ArticleHeaders getArticleHeaders(String search, int count) throws IOException  {

        URL url = null;

        url = new URL(HOST + "liste/?app=" + getAuthenticationKey() + "&query=" + search.replace(" ", "+") + "&anz=" + count );

        // Converting the string into an object
        return mapper.readValue(stringResponseFromURL(url), ArticleHeaders.class);
    }


    /**
     * Fetches the corresponding article for the given articleKey
     *
     * @param articleKey the "schluessel" of the wanted article
     * @return An article-object
     * @throws IOException in case a problem with the connection to the api occurred
     */
    public Article getArticle(String articleKey) throws IOException {
        URL url = null;
        url = new URL(HOST + "aussendung?app=" + getAuthenticationKey() + "&schluessel=" + articleKey);

        // Converting the string into an object
        return mapper.readValue(stringResponseFromURL(url), Articles.class).getErgebnisse().get(0);
    }

    /**
     * makes a get request to the provided url and returns the received response as a string
     * @param url to with the request should be made
     * @return A string consisting of the response from the endpoint
     * @throws IOException In case a problem occurred while connected to the endpoint
     */
    private String stringResponseFromURL(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        InputStream stream = null;
        stream = con.getInputStream();
        // Create byte array
        byte[] byteResponse = stream.readAllBytes();
        con.disconnect();


        // Converting the byte array to a string
        return new String(byteResponse, StandardCharsets.UTF_8);
    }

    /**
     * Fetches the private api_key for connecting to APA from a file located in resources/com/ode22/catnews_origins/,
     * which is intentionally not checked into the repository.
     *
     * @return the api_key for the APA api as a String
     */
    private String getAuthenticationKey() {
        try (InputStream input = new FileInputStream("src/main/resources/com/ode22/catnews_origins/config.properties")) {

            Properties prop = new Properties();

            // load the config file
            prop.load(input);

            // fetch the property and return it
            return prop.getProperty("apa.key");

        } catch (IOException ex) {
            throw new RuntimeException("There seems to be a problem with fetching the apa-api authentication key from the file. Please check that the file 'config.properties' is present at 'src/main/resources/com/ode22/catnews_origins/' and contains a property called 'apa.key'. If all of that is the case, please write a issue in our repository.");
        }
    }
}
