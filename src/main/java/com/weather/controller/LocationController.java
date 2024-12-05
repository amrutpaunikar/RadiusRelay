package com.weather.controller;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weather.response.Location;

@RestController
public class LocationController {
//	@PostMapping("/save-location")
//    public String saveLocation(@RequestBody Location location) {
//        System.out.println("User's Location - Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());
//        return "Location saved successfully!";
//    }

//Algorithm is here
	
	
    public static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; 
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; 
    }

  
    public static boolean isWithinCircle(double centerLat, double centerLon, double radius, double pointLat, double pointLon) {
        double distance = haversineDistance(centerLat, centerLon, pointLat, pointLon);
        return distance <= radius;
    }

    public static void main(String[] args) {
        double centerLat = 37.7749;  
        double centerLon = -122.4194;
        double radius = 0.5;  

        double pointLat = 37.7745;  
        double pointLon = -122.4191;

        if (isWithinCircle(centerLat, centerLon, radius, pointLat, pointLon)) {
            System.out.println("Point is within the circle.");
        } else {
            System.out.println("Point is outside the circle.");
        }
    
}

}  
   

