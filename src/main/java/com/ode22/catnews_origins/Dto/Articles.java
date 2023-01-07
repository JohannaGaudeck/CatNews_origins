package com.ode22.catnews_origins.Dto;

import java.util.List;
import java.util.Map;

public class Articles {
    Map<String, Object> meta;
    List<Article> ergebnisse;
    String error;

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public List<Article> getErgebnisse() {
        return ergebnisse;
    }

    public void setErgebnisse(List<Article> ergebnisse) {
        this.ergebnisse = ergebnisse;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
