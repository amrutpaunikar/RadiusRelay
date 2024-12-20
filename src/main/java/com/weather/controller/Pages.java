package com.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weather.response.CurrentWeather;
import com.weather.response.DailyWeather;
import com.weather.response.WeatherData;
import com.weather.service.WeatherService;

@Controller
public class Pages {
	
	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/location")
	public String lot() {
		return "location";
	}
	@PostMapping("/submitMessage")
    public String submitMessage(@RequestParam String message, @RequestParam String latitude, @RequestParam String longitude, Model model) {
        try {
            // Attempt to parse the latitude and longitude as doubles
            double lat = Double.parseDouble(latitude);
            double lon = Double.parseDouble(longitude);
            
            // Check for invalid coordinates (0, 0 or any other condition you want to handle)
            if (lat == 0 || lon == 0) {
                model.addAttribute("error", "Invalid coordinates provided. Please enter valid latitude and longitude values.");
                return "error"; // Return an error view if coordinates are invalid
            }
            
            // Add message and coordinates to model for processing or saving
            model.addAttribute("message", message);
            model.addAttribute("latitude", lat);
            model.addAttribute("longitude", lon);
            System.out.println(message);
            System.out.println(lon);
            System.out.println(lat);
        } catch (NumberFormatException e) {
        	e.printStackTrace();
            // Handle invalid coordinates (e.g., show an error message)
            model.addAttribute("error", "Invalid coordinates provided. Please enter valid latitude and longitude values.");
            return "error"; // Return an error view if coordinates are invalid
        }

        // Process the message or save data here if needed
        return "confirm"; // Redirect to a success page or return a success view
    }



	
	@GetMapping("/forntend")
	public String k() {
		return "forntend";
	}

	@PostMapping("/forntend")
    public String frontend(@RequestParam("city") String city, Model model) throws Exception {
		String weatherData = weatherService.getWeather(city);
		
		WeatherData WeatherData = weatherService.parseAndSaveWeatherData(weatherData);
		CurrentWeather current = WeatherData.getCurrent();
		List<DailyWeather> dailyData = WeatherData.getDailyData();
		

	    if (current == null) {
	        // Handle null case (optional, you could also return an error view)
	        current = new CurrentWeather();
	        current.setIcon_num("not");
	        current.setSummary("No data available");
	        current.setTemperature(0.0);
	    }else{
	    	model.addAttribute("city", city);
	        model.addAttribute("current", current);
	        model.addAttribute("dailyData", dailyData);
			
	    }
		
		model.addAttribute("city", city);
        model.addAttribute("current", current);
        model.addAttribute("dailyData", dailyData);
		
    	return "forntend";
    }
}
