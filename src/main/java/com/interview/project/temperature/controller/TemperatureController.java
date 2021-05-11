package com.interview.project.temperature.controller;

import com.interview.project.temperature.exception.TemperatureNotFoundException;
import com.interview.project.temperature.model.TemperatureUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class TemperatureController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private TemperatureUrl temperatureData;

    @GetMapping("/temperature/{id}")
    public String getTemperature(@PathVariable("id") String zipcode){

        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(temperatureData.getUrl())
                .path("")
                .query("id={keyword}&appid={appid}")
                .buildAndExpand(zipcode,temperatureData.getApiKey());

        String uri = uriComponents.toUriString();

        ResponseEntity<String> response= restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

        return response.getBody().toString();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void carNotFoundHandler(TemperatureNotFoundException ex) {
        log.error("Entering and leaving CarController : TemperatureNotFoundHandler");
    }
}
