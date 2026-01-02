package com.tapas.orderservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tapas.orderservice.service.InvoiceClient;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	
	private final InvoiceClient invoiceClient;
	
	public OrderController(InvoiceClient invoiceClient) {
		this.invoiceClient=invoiceClient;
	}
	
	
	@GetMapping("/status")
	public String orderServiceStatus() {
		
		return "Order Status is okay";
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<String> getOrderInvoiceId(@PathVariable Long id) {
		
		 System.out.println("Order Id is: "+id);
		 
		
		  
		return ResponseEntity.ok(invoiceClient.getPaymentId(id));
		
	}

}
