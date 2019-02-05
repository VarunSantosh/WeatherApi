package com.finleap.weather.service;

import com.finleap.weather.model.WeatherResponse;
import com.finleap.weather.owmclient.OwmRestTemplate;
import com.finleap.weather.utility.CityUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WeatherService {

    @Autowired
    private CityUtility cityUtil;

    @Autowired
    private OwmRestTemplate owmRestTemplate;

    public WeatherResponse getAverageTemperatureAndPressure (String cityName) throws Exception {
        Long cityId = cityUtil.getCityId(cityName);
        return owmRestTemplate.getResponse(cityId);
    }
}
