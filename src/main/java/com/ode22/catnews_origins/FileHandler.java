package com.ode22.catnews_origins;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ode22.catnews_origins.Dto.Article;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import java.awt.Desktop;

/**
 * Class containing all methods interacting with files
 */
public class FileHandler {
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * Writes (appends) the received article-object under "Zeitungsablage/{todaysDate}.txt"
     * @param article the article that should be saved
     * @throws IOException in case a problem with opening or writing to the file happens.
     */
    public void saveArticle(Article article) throws IOException {
        File file = new File("Zeitungsablage/" + LocalDate.now() + ".md");
        FileWriter myWriter = new FileWriter(file, true);
        myWriter.write(article.toMarkDownString());
        myWriter.close();
    }

    /**
     * Opens the file saved under Zeitungsablage/{todaysDate}.txt
     * @throws IOException
     */
    public void openDailyFile() throws IOException {
        File file = new File("Zeitungsablage/" + LocalDate.now() + ".txt");
        if (!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()){
            desktop.open(file);
        }



    }
}
