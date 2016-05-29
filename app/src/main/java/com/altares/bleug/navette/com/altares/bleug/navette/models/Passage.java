package com.altares.bleug.navette.com.altares.bleug.navette.models;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by bleug on 27/05/2016.
 * <p/>
 * Passage represents a departure time of the shuttle
 */
public class Passage implements Comparable<Passage> {
    private static final DateFormat DF = new SimpleDateFormat("HH:mm", Locale.US);

    private Way way;
    private Calendar time;
    private boolean pmr = false;
    private boolean tgif = false;

    public Passage(Way way, Calendar time) {
        this.way = way;
        this.time = trim(time);
    }

    public Passage(Way way, Calendar time, boolean pmr, boolean tgif) {
        this.way = way;
        this.time = trim(time);
        this.pmr = pmr;
        this.tgif = tgif;
    }

    public Way getWay() {
        return way;
    }

    public void setWay(Way way) {
        this.way = way;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public boolean isPmr() {
        return pmr;
    }

    public void setPmr(boolean pmr) {
        this.pmr = pmr;
    }

    public boolean isTgif() {
        return tgif;
    }

    public void setTgif(boolean tgif) {
        this.tgif = tgif;
    }

    public String timeToString() {
        return time != null ? DF.format(time.getTime()) : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passage passage = (Passage) o;

        return time != null ? time.equals(passage.time) : passage.time == null;

    }

    @Override
    public int hashCode() {
        return time != null ? time.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Passage{" +
                "way=" + way +
                ", time=" + timeToString() +
                ", pmr=" + pmr +
                ", tgif=" + tgif +
                '}';
    }

    @Override
    public int compareTo(@NonNull Passage another) {
        return time != null ? time.compareTo(another.time) : -1;
    }

    private Calendar trim(Calendar cal) {
        return new GregorianCalendar(0, 0, 0, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), 0);
    }

}
