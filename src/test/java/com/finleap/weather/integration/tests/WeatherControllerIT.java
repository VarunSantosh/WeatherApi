package com.finleap.weather.integration.tests;

import com.finleap.weather.WeatherApiApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testRetrieveWeatherDetail() {

         String response = restTemplate.getForObject(createURLWithPort("/v1/data?city=kosti"), String.class);

        String expected = "{" +
                "\"avg_temperature\":{" +
                "\"day_time\":\"33.26\"," +
                "\"night_time\":\"21.32\"" +
                "}," +
                "\"avg_pressure\":\"975.35\"" +
                "}";

        Assert.assertEquals(expected, response);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
