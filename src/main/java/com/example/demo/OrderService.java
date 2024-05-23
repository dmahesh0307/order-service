package com.example.demo;

import java.util.List;

public interface OrderService {

	public OrderDto addOrder(OrderDto orderDto);
	public OrderDto getOrder(Long orderId) throws ResourceNotFoundException;
	public List<OrderDto> getOrders();
	public OrderDto updateOrder(Long orderId, OrderDto orderDto)  throws ResourceNotFoundException;
	public void deleteOrder(Long orderId);
}
