package com.weather.kafka.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfiguration {
	
	@KafkaListener( topics = "location-update-topic", groupId = "springboot-group-1")
	public void updatedLocation(String value) {
	System.out.println(value);	
	}
	
}

