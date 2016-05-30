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
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by bleug on 28/05/2016.
 * <p/>
 * Test
 */
@RunWith(JUnit4.class)
public class PassageUnitTest {

    private static Calendar cal1, cal2, cal3;
    private static Passage passage1, passage2, passage3, passage4;

    @BeforeClass
    public static void setUp() {
        cal1 = new GregorianCalendar();
        cal1.set(Calendar.HOUR_OF_DAY, 9);
        cal1.set(Calendar.MINUTE, 30);

        cal2 = new GregorianCalendar();
        cal2.setTime(cal1.getTime());
        cal2.add(Calendar.MINUTE, 5);
        cal2.add(Calendar.DAY_OF_MONTH, -1);

        cal3 = new GregorianCalendar();
        cal3.setTime(cal1.getTime());
        cal3.add(Calendar.MINUTE, 10);
        cal3.add(Calendar.DAY_OF_MONTH, -2);

        passage1 = new Passage(Way.FROM, cal1);

        passage2 = new Passage(Way.FROM, cal2);

        passage3 = new Passage(null, null, false, false);
        passage3.setWay(Way.TO);
        passage3.setTime(cal3);
        passage3.setPmr(true);
        passage3.setTgif(true);

        passage4 = new Passage(null, null);
    }

    @AfterClass
    public static void tearDown() {
        cal1 = null;
        cal2 = null;
        cal3 = null;
        passage1 = null;
        passage2 = null;
        passage3 = null;
        passage4 = null;
    }

    @Test
    public void testConstructor() {
        assertEquals(Way.FROM, passage1.getWay());
        assertFalse(passage1.isPmr());
        assertFalse(passage1.isTgif());

        assertEquals(Way.TO, passage3.getWay());
        assertTrue(passage3.isPmr());
        assertTrue(passage3.isTgif());
    }

    @Test
    public void testOrdering() {
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

    @Test
    public void testToString() {
        assertEquals("Passage{way=TO, time=09:40, pmr=true, tgif=true}", passage3.toString());
    }

    @Test
    public void testTimeToString() {
        assertEquals("09:30", passage1.timeToString());
        assertNull(passage4.getTime());
        assertEquals("", passage4.timeToString());
    }

    @Test
    public void testCompareTo() {
        assertEquals(-1, passage1.compareTo(passage2));
        assertEquals(-1, passage4.compareTo(passage1));
    }

    @Test
    public void testEquals() {
        assertTrue(passage1.equals(passage1));
        assertFalse(passage1.equals(null));
        assertFalse(passage1.equals(1L));
        assertFalse(passage1.equals(passage2));
        assertFalse(passage4.equals(passage1));
    }

    @Test
    public void testHashCode() {
        assertEquals(passage1.getTime().hashCode(), passage1.hashCode());
        assertEquals(0, passage4.hashCode());
    }
}
