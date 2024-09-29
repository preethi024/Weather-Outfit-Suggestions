package com.Outfits.Weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Outfits.Weather.model.WeatherSuggestion;
import com.Outfits.Weather.repository.WeatherSuggestionRepository;
import com.Outfits.Weather.service.WeatherService;

@RestController
@RequestMapping("/api")
public class WeatherConditionController {
	
	 @Autowired
	    private WeatherSuggestionRepository weatherSuggestionRepository;

	    @Autowired
	    private WeatherService weatherService; 
	
	 @GetMapping("/") 
	    public String redirectToIndex() {
	        return "forward:/index.html"; 
	    }
	 @GetMapping("/suggestions/outfit")
	 public String getOutfitSuggestion( @RequestParam double latitude,  
	            @RequestParam double longitude) {
		 String weatherCondition=weatherService.getWeatherCondition(latitude,longitude);
		 WeatherSuggestion suggestion=weatherSuggestionRepository.findByWeatherCondition(weatherCondition);
		 if(suggestion !=null) {
			 return suggestion.getSuggestion();
		 }
		 else {
			 return "No Suggestion available for this Weather";
		 }
	 }
	 @GetMapping("/suggestions/all")
	 public List<WeatherSuggestion> getAllSuggestions() {
	     return weatherSuggestionRepository.findAll();
	 }

}
