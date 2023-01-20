package com.ode22.catnews_origins;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class Datehandler {
    private int year, month, day;

    public void set_date(String str){
        String[] split = str.split("-");
        this.year = Integer.parseInt(split[0]);
        this.month = Integer.parseInt(split[1]);
        this.day = Integer.parseInt(split[2]);

    }


   public long get_unix_timestamp(String str) {
        set_date(str);
        // Get LocalDate object
        LocalDate localDate = LocalDate.of(this.year, this.month, this.day);

        // Convert LocalDate to Instant with ZoneOffSet
        Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);

        // Get unix timestamp from Instant
        long epochSecond = instant.getEpochSecond();
        //System.out.println("Unix timestamp: " + epochSecond);

        return epochSecond;
    }
}
