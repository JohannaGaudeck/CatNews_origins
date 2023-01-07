package com.ode22.catnews_origins.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article {
    @JsonProperty("TITEL")
    String titel;
    @JsonProperty("UTL")
    String utl;
    @JsonProperty("INHALT")
    String inhalt;
    @JsonProperty("RUECKFRAGEHINWEIS")
    String rueckfragehinweis;
    @JsonProperty("DATUM")
    String datum;
    @JsonProperty("ZEIT")
    String zeit;
    @JsonProperty("ZEITSTEMPEL")
    String zeitstempel;
    @JsonProperty("ANHANG")
    List<Object> anhang;
    @JsonProperty("WEBLINK")
    String weblink;
    @JsonProperty("EMITTENT")
    String emittent;
    @JsonProperty("EMITTENTID")
    String emittentid;

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getUtl() {
        return utl;
    }

    public void setUtl(String utl) {
        this.utl = utl;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public String getRueckfragehinweis() {
        return rueckfragehinweis;
    }

    public void setRueckfragehinweis(String rueckfragehinweis) {
        this.rueckfragehinweis = rueckfragehinweis;
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

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String getEmittent() {
        return emittent;
    }

    public void setEmittent(String emittent) {
        this.emittent = emittent;
    }

    public String getEmittentid() {
        return emittentid;
    }

    public void setEmittentid(String emittentid) {
        this.emittentid = emittentid;
    }
}
