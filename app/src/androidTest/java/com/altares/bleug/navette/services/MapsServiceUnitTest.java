package com.altares.bleug.navette.services;

import android.util.Log;

import com.altares.bleug.navette.com.altares.bleug.navette.services.MapsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static com.altares.bleug.navette.com.altares.bleug.navette.models.Way.FROM;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

@RunWith(JUnit4.class)
public class MapsServiceUnitTest {

    @Test
    public void testTrafficModifier() {
        MapsService mMapsService = mock(MapsService.class, withSettings().verboseLogging());
        ObjectNode mockData = null;
        try {
            mockData = (ObjectNode) new ObjectMapper().readTree("{\"destination_addresses\":[\"1 Rond-Point de la Défense, 92400 Courbevoie, France\"],\"origin_addresses\":[\"55 Avenue des Champs Pierreux, 92000 Nanterre, France\"],\"rows\":[{\"elements\":[{\"distance\":{\"text\":\"2.8 km\",\"value\":2757},\"duration\":{\"text\":\"8 mins\",\"value\":462},\"duration_in_traffic\":{\"text\":\"10 mins\",\"value\":621},\"status\":\"OK\"}]}],\"status\":\"OK\"}");
        } catch (IOException e) {
            Log.e("testTrafficModifier", "IOE", e);
        }
        when(mMapsService.getTrafficModifier(FROM)).thenCallRealMethod();
        when(mMapsService.callDistanceMatrix(FROM)).thenReturn(mockData);

        Integer from = mMapsService.getTrafficModifier(FROM);
        verify(mMapsService).callDistanceMatrix(FROM);
        assertEquals(159, from.intValue());
    }

}