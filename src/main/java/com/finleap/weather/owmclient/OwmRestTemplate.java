package com.finleap.weather.owmclient;

import com.finleap.weather.exception.OwmClientException;
import com.finleap.weather.model.WeatherResponse;
import com.finleap.weather.utility.FormatUtility;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OwmRestTemplate {

    @Value("${weather.owm.api.key}")
    private String owmApiKey;

    @Value("${weather.owm.api.url}")
    private String owmApiUrl;

    private static final String forecast = "forecast";
    private static final String appid = "appid";
    private static final String id = "id";

    public WeatherResponse getResponse(Long cityId) throws OwmClientException, ParseException {

        String url = new StringBuilder()
                .append(owmApiUrl)
                .append(forecast)
                .append("?")
                .append(id)
                .append("=")
                .append(cityId)
                .append("&")
                .append(appid)
                .append("=")
                .append(owmApiKey).toString();

        String result = callOwmRestApi(url);

        return FormatUtility.mapToResponse(result);

    }

    @Cacheable("weatherDetails")
    public String callOwmRestApi(String url) throws OwmClientException {
        log.info("calling owm service");
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        if (StringUtils.isEmpty(result)) {
            log.info("OWM service rsturned no data.");
            throw new OwmClientException("OWM call restrned no data");
        }
        return result;
    }
}
