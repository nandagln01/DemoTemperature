package com.interview.project.temperature.config;

import com.interview.project.temperature.model.TemperatureUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan
public class TemperatureConfiguration {

    @Value("${temperature.url}")
    private String url;

    @Value("${temperature.apikey}")
    private String apikey;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        c.setIgnoreUnresolvablePlaceholders(true);
        return c;
    }

    @Bean
    public TemperatureUrl temperatureUrl() {

       TemperatureUrl temperatureUrl = new TemperatureUrl();
       temperatureUrl.setUrl(url);
       temperatureUrl.setApiKey(apikey);
       return temperatureUrl;
    }
}
