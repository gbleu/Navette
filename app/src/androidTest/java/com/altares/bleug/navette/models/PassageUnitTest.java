package com.altares.bleug.navette.models;

import com.altares.bleug.navette.com.altares.bleug.navette.models.Passage;
import com.altares.bleug.navette.com.altares.bleug.navette.models.Way;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by bleug on 28/05/2016.
 * <p/>
 * Test
 */
@RunWith(JUnit4.class)
public class PassageUnitTest {

    private static Calendar cal1, cal2;
    private static Passage passage1, passage2;

    @BeforeClass
    public static void setUp() {
        cal1 = new GregorianCalendar();
        cal1.set(Calendar.HOUR_OF_DAY, 9);
        cal1.set(Calendar.MINUTE, 30);

        cal2 = new GregorianCalendar();
        cal2.setTime(cal1.getTime());
        cal2.add(Calendar.MINUTE, 5);
        cal2.add(Calendar.DAY_OF_MONTH, -1);

        passage1 = new Passage(Way.FROM, cal1);
        passage2 = new Passage(Way.FROM, cal2);
    }

    @AfterClass
    public static void tearDown() {
        cal1 = null;
        cal2 = null;
        passage1 = null;
        passage2 = null;
    }

    @Test
    public void testConstructor() {
        assertEquals(Way.FROM, passage1.getWay());
        assertEquals("09:30", passage1.timeToString());
        assertFalse(passage1.isPmr());
        assertFalse(passage1.isTgif());

        assertEquals(Way.FROM, passage2.getWay());
        assertEquals("09:35", passage2.timeToString());
        assertFalse(passage2.isPmr());
        assertFalse(passage2.isTgif());
    }

    @Test
    public void testOrdering() {
        assertFalse(passage1.equals(passage2));
        assertEquals(-1, passage1.compareTo(passage2));

        List<Passage> passages = new ArrayList<>();
        passages.add(passage2);
        passages.add(passage1);

        assertEquals(2, passages.size());
        assertEquals(passage2, passages.get(0));
        assertEquals(passage1, passages.get(1));

        Collections.sort(passages);

        assertEquals(passage1, passages.get(0));
        assertEquals(passage2, passages.get(1));
    }
}
