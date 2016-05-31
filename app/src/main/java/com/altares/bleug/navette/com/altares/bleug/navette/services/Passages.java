package com.altares.bleug.navette.com.altares.bleug.navette.services;

import com.altares.bleug.navette.com.altares.bleug.navette.models.Passage;
import com.altares.bleug.navette.com.altares.bleug.navette.models.Way;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import static com.altares.bleug.navette.com.altares.bleug.navette.models.Way.FROM;
import static com.altares.bleug.navette.com.altares.bleug.navette.models.Way.TO;

/**
 * Created by bleug on 31/05/2016.
 * Passages data
 */
public class Passages {
    private static Passages ourInstance = new Passages();

    private Map<Way, NavigableSet<Passage>> passages;

    private Passages() {
        passages = new HashMap<>();
    }

    public static Passages getInstance() {
        return ourInstance;
    }

    public void init() {
        NavigableSet<Passage> to = new TreeSet<>(), from = new TreeSet<>();

        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 7, 7, 0), true, false));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 7, 15, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 7, 22, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 7, 30, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 7, 37, 0), true, false));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 7, 45, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 7, 51, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 7, 55, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 0, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 5, 0), true, false));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 10, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 15, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 20, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 25, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 30, 0), true, false));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 35, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 40, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 45, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 50, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 8, 55, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 0, 0), true, false));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 5, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 10, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 15, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 20, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 25, 0), true, false));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 30, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 35, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 40, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 45, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 50, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 9, 55, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 10, 0, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 10, 10, 0)));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 12, 15, 0), true, false));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 12, 45, 0), true, false));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 13, 15, 0), true, false));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 13, 45, 0), true, false));
        to.add(new Passage(TO, new GregorianCalendar(0, 0, 0, 14, 15, 0), true, false));
        passages.put(TO, to);

//        FROM	12:00	TRUE
//        FROM	12:30	TRUE
//        FROM	13:00	TRUE
//        FROM	13:30	TRUE
//        FROM	14:00	TRUE
//        FROM	16:07		TRUE
//        FROM	16:15		TRUE
//        FROM	16:22		TRUE
//        FROM	16:30	TRUE
//        FROM	16:37
//        FROM	16:45
//        FROM	16:52
//        FROM	17:00	TRUE
//        FROM	17:05
//        FROM	17:10
//        FROM	17:15
//        FROM	17:20
//        FROM	17:25
//        FROM	17:30	TRUE
//        FROM	17:35
//        FROM	17:40
//        FROM	17:45
//        FROM	17:50
//        FROM	17:55
//        FROM	18:00	TRUE
//        FROM	18:05
//        FROM	18:10
//        FROM	18:15
//        FROM	18:20
//        FROM	18:25
//        FROM	18:30	TRUE
//        FROM	18:35
//        FROM	18:40
//        FROM	18:45
//        FROM	18:50
//        FROM	18:55
//        FROM	19:00	TRUE
//        FROM	19:07
//        FROM	19:15
//        FROM	19:22
//        FROM	19:30	TRUE
//        FROM	19:37
//        FROM	19:45
//        FROM	19:52
//        FROM	20:07
        passages.put(FROM, from);
    }

    public NavigableSet<Passage> get(Way way) {
        return this.passages.get(way);
    }

    public Passage next(Way way) {
        return this.next(way, new GregorianCalendar());
    }

    public Passage next(Way way, Calendar cal) {
        return this.get(way).higher(new Passage(null, cal));
    }

}
