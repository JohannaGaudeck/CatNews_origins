package com.ode22.catnews_origins.Dto;

import java.util.List;
import java.util.Map;

public class ArticleHeaders {
    Map<String, Integer> meta;
    List<ArticleHeader> ergebnisse;

    public Map<String, Integer> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Integer> meta) {
        this.meta = meta;
    }

    public List<ArticleHeader> getErgebnisse() {
        return ergebnisse;
    }

    public void setErgebnisse(List<ArticleHeader> ergebnisse) {
        this.ergebnisse = ergebnisse;
    }
}
