package com.cauadev.picpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cauadev.picpay.model.Status;

@RestController
public class NotificationController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/status")
	public ResponseEntity<?> notificationPayment(@RequestParam(name = "ref") String ref){
		
		if(ref != null) {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("x-picpay-token", "3eaed159-079e-4885-9955-35a99175178c");
			HttpEntity<?> entity = new HttpEntity<>(headers);
			
			 ResponseEntity<Status> res = restTemplate.exchange("https://appws.picpay.com/ecommerce/public/payments/"+ref+"/status"
					, HttpMethod.GET
					, entity
					, Status.class);
			
			return ResponseEntity.ok(res.getBody());
			
		}
		
		return ResponseEntity.badRequest().body(false);
		
	}

}
