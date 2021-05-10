package com.cauadev.picpay.controller;

import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cauadev.picpay.model.Cliente;
import com.cauadev.picpay.model.Payment;
import com.cauadev.picpay.model.PicPay;
import com.cauadev.picpay.model.Product;

@RestController
public class PaymentController {
	
	@PostMapping("/payment")
	public ResponseEntity<?> requestPayment(){
		
		PicPay picPay = new PicPay();
		Product product = new Product();
		Cliente cliente = new Cliente();
		
		String ref = String.valueOf(new Random().nextInt(99999));
		
		product.setRef(ref);
		product.setNome("Vip Beta");
		product.setValor(3.0);
		
		cliente.setCpf("11361238252");
		cliente.setEmail("cauadev@gmail.com");
		cliente.setNome("Cauã");
		cliente.setSobrenome("Da Silva Nunes");
		cliente.setTelefone("81984019242");
		
		Payment res = picPay.requestPayment(product, cliente);
		
		if(res.getMessage() != null) {
			ResponseEntity.badRequest().body(res.getMessage());
		}else {
			return ResponseEntity.ok(res);
		}
		return ResponseEntity.badRequest().body("erro inesperado :0");
	}

}
