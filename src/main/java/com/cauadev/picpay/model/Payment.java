package com.cauadev.picpay.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Payment {

	private String message;
	private String[] erros;
	private String referenceId;
	private String paymentUrl;
	private String expiresAt;
	private QRcode qrcode;
}
