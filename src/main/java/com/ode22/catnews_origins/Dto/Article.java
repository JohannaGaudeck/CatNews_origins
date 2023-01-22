package com.ode22.catnews_origins.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * A class consisting of the relevant data received from the apa-api for a single article.
 * Quite a few properties have been left out, since they were not used in our program.
 * A few examples:
 *  - Anhang
 *  - EMITTENT
 *  - UTL
 *  - RUECKFRAGEHINWEIS
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
    @JsonProperty("TITEL")
    String titel;
    @JsonProperty("INHALT")
    String inhalt;
    @JsonProperty("DATUM")
    String datum;
    @JsonProperty("ZEIT")
    String zeit;
    @JsonProperty("WEBLINK")
    String weblink;

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getZeit() {
        return zeit;
    }

    public void setZeit(String zeit) {
        this.zeit = zeit;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String toMarkDownString() {
        return "\n\r" +
                "## " + this.titel + "\n\r" +
                "_" + this.datum + " " + this.zeit +  "_\n\r" +
                "\n" +
                this.inhalt + "\n\r" +
                "\n" +
                "> Link: " + this.weblink +
                "\n";
    }
}
