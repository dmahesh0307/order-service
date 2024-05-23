package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public OrderDto addOrder(OrderDto orderDto) {
		Order inputOrder = OrderDataMapper.generateOrderObject(orderDto);
		Order order = orderRepository.save(inputOrder);
		return OrderDataMapper.generateOrderDtoObject(order);
	}

	@Override
	public OrderDto getOrder(Long orderId) throws ResourceNotFoundException {
		// Optional<Order> order = orderRepository.findById(orderId);
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("" + orderId));
		return OrderDataMapper.generateOrderDtoObject(order);
	}

	@Override
	public List<OrderDto> getOrders() {
		List<OrderDto> orderDtos = new ArrayList<>();
		orderRepository.findAll().forEach((order) -> {
			orderDtos.add(OrderDataMapper.generateOrderDtoObject(order));
		});
		return orderDtos;
	}

	@Override
	public OrderDto updateOrder(Long orderId, OrderDto orderDto) throws ResourceNotFoundException {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("" + orderId));
		order.setOrderId(orderDto.getOrderId());
		order.setOrderType(orderDto.getOrderType());
		order.setProduct(orderDto.getProduct());
		order.setQuantity(orderDto.getQuantity());
		return OrderDataMapper.generateOrderDtoObject(orderRepository.save(order));
	}

	@Override
	public void deleteOrder(Long orderId) {
		orderRepository.deleteById(orderId);
	}

}
