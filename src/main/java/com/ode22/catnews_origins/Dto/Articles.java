package com.ode22.catnews_origins.Dto;

import java.util.List;
import java.util.Map;

/**
 *  Class identical to the response received by the apa-api on a 'article-GET' request.
 *  Consists of metaData and a list of the received articles.
 */
public class Articles {
    Map<String, Object> meta;
    List<Article> ergebnisse;

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
}
