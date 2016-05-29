package com.altares.bleug.navette.services.com.altares.bleug.navette.models;

import com.altares.bleug.navette.com.altares.bleug.navette.models.Passage;
import com.altares.bleug.navette.com.altares.bleug.navette.models.Way;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by bleug on 28/05/2016.
 * <p/>
 * Test
 */
public class PassageUnitTest extends TestCase {

    private Calendar cal1, cal2;
    private Passage passage1, passage2;

    @Override
    protected void setUp() {
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

    @Override
    protected void tearDown() {
        cal1 = null;
        cal2 = null;
        passage1 = null;
        passage2 = null;
    }

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
