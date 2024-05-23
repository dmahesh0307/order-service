package com.example.demo;

public class OrderDataMapper {

	private OrderDataMapper() {
		throw new IllegalStateException("Utility class");
	}

	public static Order generateOrderObject(OrderDto orderDto) {
		Order order = new Order();
		order.setOrderId(orderDto.getOrderId());
		order.setOrderType(orderDto.getOrderType());
		order.setProduct(orderDto.getProduct());
		order.setQuantity(orderDto.getQuantity());
		return order;
	}

	public static OrderDto generateOrderDtoObject(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setOrderId(order.getOrderId());
		orderDto.setOrderType(order.getOrderType());
		orderDto.setProduct(order.getProduct());
		orderDto.setQuantity(order.getQuantity());
		return orderDto;
	}
}
