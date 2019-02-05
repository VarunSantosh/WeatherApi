package com.finleap.weather.utility;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CityUtility {

    public static final Map<String, Long> cityIdMap = new HashMap<>();

    static {
        try {
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("./src/main/resources/json/city.list.json"));
            log.info("Loading city and their respective id map from the city.list.json file");

            for (Object o : a) {
                JSONObject city = (JSONObject) o;
                cityIdMap.put(String.valueOf(city.get("name")).toLowerCase().trim(), Long.parseLong(String.valueOf(city.get("id"))));
            }
        } catch (ParseException | IOException ex) {
            log.error("Error reading or loading the city json file");
        }
    }

    public Long getCityId(String cityName) {
        return cityIdMap.get(cityName.toLowerCase().trim());
    }
}
