package com.Outfits.Weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Outfits.Weather.model.WeatherSuggestion;


public interface WeatherSuggestionRepository extends JpaRepository<WeatherSuggestion,Long> {
 WeatherSuggestion findByWeatherCondition(String weatherCondition);
}
