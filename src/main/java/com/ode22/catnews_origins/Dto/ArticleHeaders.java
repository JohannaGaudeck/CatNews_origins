package com.ode22.catnews_origins.Dto;

import java.util.List;
import java.util.Map;

/**
 * Class for article headers received from the APA api.
 */
public class ArticleHeaders {
    Map<String, Object> meta;
    List<ArticleHeader> ergebnisse;

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public List<ArticleHeader> getErgebnisse() {
        return ergebnisse;
    }

    public void setErgebnisse(List<ArticleHeader> ergebnisse) {
        this.ergebnisse = ergebnisse;
    }
}
