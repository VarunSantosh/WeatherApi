package com.finleap.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Setter
@Getter
public class Temperature {

    @ApiModelProperty(value = "Day time temperature")
    @NotNull
    @JsonProperty("day_time")
    public String dayTemp;

    @ApiModelProperty(value = "Day time temperature")
    @NotNull
    @JsonProperty("night_time")
    public String nightTemp;
}
