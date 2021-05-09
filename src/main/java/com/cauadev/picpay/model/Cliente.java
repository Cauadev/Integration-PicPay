package com.cauadev.picpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {

	private String nome;
	private String sobrenome;
	private String cpf;
	private String email;
	private String telefone;
}
