package com.interview.project.temperature.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature implements Serializable {

    private static final long serialVersionUID = 7406210628182440902L;

    private double temp;
    private double temp_min;

    @Bean
    public Temperature weather() {
        return new Temperature();
    }

    public Temperature() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Temperature(Temperature temperature) {
        // TODO Auto-generated constructor stub
    }

    public double getTemp() {
        return temp;
    }

    @JsonProperty("temp")
    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    @JsonProperty("temp_min")
    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

}
