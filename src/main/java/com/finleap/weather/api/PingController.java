package com.finleap.weather.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PingController {

    @GetMapping("/v1/ping")
    public String ping() {
        log.info("/ping was called to check if server is up and running");
        return "Server ok";
    }
}
