package com.finleap.weather.validators;

import com.finleap.weather.utility.CityUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
@Slf4j
public class RequestValidator {

    public boolean validateCityName(String city) {
        Map<String, Long> cityMap = CityUtility.cityIdMap;
        if(StringUtils.isEmpty(cityMap.get(city))) return false;
        return true;
    }
}
