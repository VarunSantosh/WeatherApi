package com.finleap.weather.api;

import com.finleap.weather.model.WeatherResponse;
import com.finleap.weather.service.WeatherService;
import com.finleap.weather.validators.RequestValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WeatherController {

    @Autowired
    private WeatherService wService;

    @Autowired
    private RequestValidator validator;

    @ApiOperation(value = "Returns average temperature and pressure for next three days based on city", nickname =
            "GetAverageTemperatureAndPressure", response = WeatherResponse
            .class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request Processed Successfully", response =
                    WeatherResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Resource Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping("/v1/data")
    public ResponseEntity getTempAndPressure(@RequestParam(value = "city", required = true) String city) {
        log.info("Get average temperature and pressure api called with uri: {} and for city: {}", "v1//data", city);
        try {
            //validating if the city name was not passed empty
            if (StringUtils.isEmpty(city)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            //validating if the city name passed is valid or not
            if (!validator.validateCityName(city)) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

            WeatherResponse response = wService.getAverageTemperatureAndPressure(city);
            log.info("Responded to the call with the data");
            return new ResponseEntity(response, HttpStatus.OK);

        } catch (Exception ex) {
            log.error("Exception encountered while serving request: {}", ex.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
