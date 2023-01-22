package com.ode22.catnews_origins;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class Datehandler {
    private int year, month, day;

    /**
     * splits the date to year, month and day
     * @param str the date string that should be split with the format yyyy-mm-dd
     */
    public void set_date(String str){
        String[] split = str.split("-");
        this.year = Integer.parseInt(split[0]);
        this.month = Integer.parseInt(split[1]);
        this.day = Integer.parseInt(split[2]);
    }


    /**
     * gets the unix timestamp from a given date string
     * @param str the date string with the format yyyy-mm-dd
     * @return long unix timestamp
     */
    public long get_unix_timestamp(String str) {
        set_date(str);
        // Get LocalDate object
        LocalDate localDate = LocalDate.of(this.year, this.month, this.day);

        // Convert LocalDate to Instant with ZoneOffSet
        Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);

        // Get unix timestamp from Instant
        long epochSecond = instant.getEpochSecond();

        return epochSecond;
    }
}
