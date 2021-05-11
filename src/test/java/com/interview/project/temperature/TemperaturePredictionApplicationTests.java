package com.interview.project.temperature;

import com.interview.project.temperature.model.TemperatureUrl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WebMvcTest
class TemperaturePredictionApplicationTests {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private TemperatureUrl temperatureData;

    @Autowired
	private MockMvc mockMvc;


	@Test
	public void getTemperatureShouldReturnTemperature() throws Exception{

		String zipcode = "524901";

		UriComponents uriComponents = UriComponentsBuilder
				.newInstance()
				.scheme("http")
				.host(temperatureData.getUrl())
				.path("")
				.query("id={keyword}&appid={appid}")
				.buildAndExpand(zipcode,temperatureData.getApiKey());

		String uri = uriComponents.toUriString();

		ResponseEntity<String>  response = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

		Assertions.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void getTemperatureNotFound() throws Exception{

		mockMvc.perform(MockMvcRequestBuilders.get("/api/temperature/1234"))
				.andExpect(status().isNotFound());
	}


}
