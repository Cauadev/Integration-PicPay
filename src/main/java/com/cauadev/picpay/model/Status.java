package com.cauadev.picpay.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {  
	
	private String referenceId;
	private String status;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private Double value;

}
