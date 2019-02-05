package com.finleap.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@Setter
@Getter
@ToString
public class WeatherResponse {

    @ApiModelProperty(value = "Temperature")
    @NotNull
    @JsonProperty("avg_temperature")
    public Temperature temperature;

    @ApiModelProperty(value = "Pressure")
    @NotNull
    @JsonProperty("avg_pressure")
    public String pressure;
}
