package com.cauadev.picpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
	
	private String nome;
	private String ref;
	private Double valor;

}
