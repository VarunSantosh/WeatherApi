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
public class PingControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testCheckServer() {

        String response = restTemplate.getForObject(createURLWithPort("/v1/ping"), String.class);

        String expected = "Server ok";

        Assert.assertEquals(expected, response);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
