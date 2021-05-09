package com.cauadev.picpay.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PicPay {
	
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private String urlCallBack = "http://localhost:8085/notification";
	
	private String urlReturn = "http://localhost:8085/user";
	
	private String x_picpay_token = "YOUR_TOKEN";
	
	private String x_seller_token = "YOUR_TOKEN";
	
	
	public Payment requestPayment(Product product, Cliente cliente){
		
		RestTemplate restTemplate = new RestTemplate();
		
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> buyer = new HashMap<String, Object>();
		data.put("referenceId", product.getRef());
		data.put("callbackUrl", this.urlCallBack);
		data.put("returnUrl", this.urlReturn);
		data.put("value", product.getValor());
		
		buyer.put("firstName", cliente.getNome());
		buyer.put("lastName", cliente.getSobrenome());
		buyer.put("document", cliente.getCpf());
		buyer.put("email", cliente.getEmail());
		buyer.put("phone", cliente.getTelefone());
		
		data.put("buyer", buyer);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("x-picpay-token", this.x_picpay_token);
		HttpEntity<?> entity = new HttpEntity<>(data, headers);
		
		 Payment res = restTemplate.postForObject("https://appws.picpay.com/ecommerce/public/payments"
				, entity
				, Payment.class);
		
		return res;
	}
	
	

}
