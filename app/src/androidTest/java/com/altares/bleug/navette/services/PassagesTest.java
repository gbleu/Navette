package com.altares.bleug.navette.services;

import com.altares.bleug.navette.com.altares.bleug.navette.services.Passages;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.altares.bleug.navette.com.altares.bleug.navette.models.Way.TO;
import static junit.framework.Assert.assertEquals;

/**
 * Created by bleug on 31/05/2016.
 * Test
 */
@RunWith(JUnit4.class)
public class PassagesTest {

    @BeforeClass
    public static void setUp() {
        Passages.getInstance().init();
    }

    @Test
    public void testNext() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 58);
        assertEquals("09:00", Passages.getInstance().next(TO, cal).timeToString());
    }

}
