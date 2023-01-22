package com.ode22.catnews_origins.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class for article header received from the APA api.
 */
public class ArticleHeader {
    @JsonProperty("TITEL")
    String titel;

    @JsonProperty("LEAD")
    String lead;

    @JsonProperty("DATUM")
    String datum;

    @JsonProperty("ZEIT")
    String zeit;

    @JsonProperty("EMITTENTID")
    String emittentid;
    @JsonProperty("EMITTENT")
    String emittent;

    @JsonProperty("ZEITSTEMPEL")
    String zeitstempel;

    @JsonProperty("ANHANG")
    List<Object> anhang;

    @JsonProperty("SCHLUESSEL")
    String schluessel;

    @JsonProperty("WEBLINK")
    String weblink;

    public String getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(String zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public List<Object> getAnhang() {
        return anhang;
    }

    public void setAnhang(List<Object> anhang) {
        this.anhang = anhang;
    }

    public String getSchluessel() {
        return schluessel;
    }

    public void setSchluessel(String schluessel) {
        this.schluessel = schluessel;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
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

    public String getEmittentid() {
        return emittentid;
    }

    public void setEmittentid(String emittentid) {
        this.emittentid = emittentid;
    }

    public String getEmittent() {
        return emittent;
    }

    public void setEmittent(String emittent) {
        this.emittent = emittent;
    }

    @Override
    public String toString() {
        return titel  + "  \n"+ datum + " " + zeit;
    }
}
