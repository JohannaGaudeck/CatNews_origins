package com.ode22.catnews_origins.Client;

import java.io.*;
import java.net.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CatClient
{


    public String getRandomCat() throws IOException, ProtocolException  {

        String host = "https://api.thecatapi.com/v1/images/search";
        URL url = null;

        url = new URL(host);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        InputStream stream = null;
        stream = con.getInputStream();
        int count = stream.available();

        // Create byte array
        byte[] b = new byte[count];

        stream.read(b);
        System.out.println(con.getResponseMessage() + con.getContent());
        String charResponse = "";

        for (byte by : b) {
            charResponse = charResponse + (char) by;
        }

        charResponse = charResponse.substring(charResponse.indexOf("url") + 6);
        charResponse = charResponse.substring(0, charResponse.indexOf('"'));
        con.disconnect();
        return charResponse;
    }
}
