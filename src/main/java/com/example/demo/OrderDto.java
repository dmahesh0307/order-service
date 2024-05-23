package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {
	private Long id;
	private String orderId;
	private String orderType;
	private String product;
	private int quantity;
	private double totalAmount;
	
	@Override
	public String toString() {
		return "OrderDto [orderId=" + orderId + ", orderType=" + orderType + ", product=" + product + ", quantity="
				+ quantity + ", totalAmount=" + totalAmount + "]";
	}
	
	
}
