package com.ode22.catnews_origins;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ode22.catnews_origins.Dto.Article;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class FileHandler {
    ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public void saveArticle(Article article) throws IOException {
        File file = new File("Zeitungsablage/" + LocalDate.now() + ".txt");
        FileWriter myWriter = new FileWriter(file, true);
        myWriter.write(mapper.writeValueAsString(article));
        myWriter.close();
    }
}
