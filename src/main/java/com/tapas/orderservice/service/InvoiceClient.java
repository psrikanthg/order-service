package com.tapas.orderservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service	
public class InvoiceClient {
	
	private final RestTemplate restTemplate;
	
	public InvoiceClient(RestTemplate restTemplate) {
		this.restTemplate=restTemplate;
	}
	
	public String getPaymentId(Long orderId) {
		
		 System.out.print("Invoice Client id: "+orderId);
		 //String url = "http://invoice-service/invoice/order/{id}";
		  String url = "http://localhost:8081/invoice/order/{id}";
		
		try {
			
			return restTemplate.getForObject(url, String.class, orderId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
		
		
	}

}
