package com.cauadev.picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cauadev.picpay.model.Notification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class NotificationController {
	
	@Autowired
	RestTemplate restTemplate;
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@PostMapping
	public ResponseEntity<?> notificationPayment(@RequestBody Notification notification){
		
		if(notification.getAuthorizationId() != null) {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("x-picpay-token", "YOUR_TOKEN");
			HttpEntity<?> entity = new HttpEntity<>(headers);
			
			ResponseEntity<String> res = restTemplate.exchange("https://appws.picpay.com/ecommerce/public/payments/"+notification.getReferenceId()+"/status"
					, HttpMethod.GET
					, entity
					, String.class);
			
			return ResponseEntity.ok(gson.toJson(res.getBody()));
			
		}
		
		return ResponseEntity.badRequest().body(false);
		
	}

}
