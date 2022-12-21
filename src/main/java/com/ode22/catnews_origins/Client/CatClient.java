package com.ode22.catnews_origins.Client;

import java.io.*;
import java.net.*;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ode22.catnews_origins.Dto.RandomCat;

public class CatClient
{

    public RandomCat getRandomCat() throws IOException {

        String host = "https://api.thecatapi.com/v1/images/search";
        URL url = new URL(host);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        InputStream stream =  con.getInputStream();
        int count = stream.available();

        // Create byte array
        byte[] b = new byte[count];

        stream.read(b);
        StringBuilder charResponse = new StringBuilder();

        for (byte by : b) {
            charResponse.append((char) by);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        RandomCat randomCat = objectMapper.readValue(charResponse.toString(), new TypeReference<List<RandomCat>>() {
        }).get(0);

        con.disconnect();

        return randomCat;
    }
}
