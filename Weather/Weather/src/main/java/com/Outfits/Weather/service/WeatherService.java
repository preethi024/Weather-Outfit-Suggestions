package com.Outfits.Weather.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Outfits.Weather.model.WeatherResponse;

@Service
public class WeatherService {

	
	@Value("${weather.api.key}") 
    private String apiKey;
	
	
	private final String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?lat={latitude}&lon={longitude}&appid={apiKey}&units=metric";

    public String getWeatherCondition(double latitude, double longitude) {
    	String url = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=%s", 
                latitude, longitude, apiKey);
                RestTemplate restTemplate = new RestTemplate();
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
          return response != null && response.getWeather() != null && !response.getWeather().isEmpty() ? 
        response.getWeather().get(0).getMain() : null;
    }
}
