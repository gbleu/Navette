package com.altares.bleug.navette.com.altares.bleug.navette.services;

import android.util.Log;

import com.altares.bleug.navette.com.altares.bleug.navette.models.Way;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.altares.bleug.navette.com.altares.bleug.navette.models.Way.TO;

/**
 * Created by bleug on 26/05/2016.
 */
public class MapsService {
    private static final String API_URL = "https://maps.googleapis.com/maps/api/distancematrix/json";
    private static final String API_KEY = "AIzaSyD4BZ2WD-XKEfCgRM2SG8byGiUn9Lsc8TE";
    private static final String LA_DEFENSE_PLACE_ID = "ChIJed0JWwRl5kcRNQRpOlQOQ1Q";
    private static final String LE_CAPITOLE_PLACE_ID = "ChIJHUkloO1k5kcRSH_vuPgKV8I";
    private static MapsService ourInstance = new MapsService();

    private MapsService() {
    }

    public static MapsService getInstance() {
        return ourInstance;
    }

    public Integer getTrafficModifier(Way way) {
        ObjectNode distanceMatrix = callDistanceMatrix(way);

        Log.d("getTrafficModifier", distanceMatrix.toString());

        int regularDuration = distanceMatrix.at("/rows/0/elements/0/duration/value").asInt();
        int trafficDuration = distanceMatrix.at("/rows/0/elements/0/duration_in_traffic/value").asInt();

        return regularDuration > 0 && trafficDuration > 0 ? trafficDuration - regularDuration : null;
    }

    public ObjectNode callDistanceMatrix(Way way) {
        if (TO.equals(way)) {
            return callDistanceMatrix(LA_DEFENSE_PLACE_ID, LE_CAPITOLE_PLACE_ID);
        } else {
            return callDistanceMatrix(LE_CAPITOLE_PLACE_ID, LA_DEFENSE_PLACE_ID);
        }
    }

    public ObjectNode callDistanceMatrix(String originPlaceId, String destinPlaceId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("origins", "place_id:" + originPlaceId)
                .queryParam("destinations", "place_id:" + destinPlaceId)
                .queryParam("departure_time", "now")
                .queryParam("traffic_model", "pessimistic")
                .queryParam("key", API_KEY);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        // Add the JSON message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        // Make the HTTP GET request, marshaling the response to an ObjectNode
        return restTemplate.getForObject(builder.toUriString(), ObjectNode.class);
    }

}
