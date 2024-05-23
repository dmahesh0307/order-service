package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public OrderDto addOrder(@RequestBody OrderDto orderDto) {
		System.out.println(" addOrder - " + orderDto);
		return orderService.addOrder(orderDto);
	}

	@GetMapping(value = "/orderId/{orderId}")
	public OrderDto getOrder(@PathVariable Long orderId) throws ResourceNotFoundException {
		System.out.println(" getOrder - " + orderId);
		return orderService.getOrder(orderId);
	}

	@GetMapping
	public List<OrderDto> getOrders() {
		System.out.println(" getOrders - ");
		return orderService.getOrders();
	}

	@PutMapping("/orderId/{orderId}")
	public OrderDto updateOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto) throws ResourceNotFoundException {
		System.out.println(" updateOrder -  param - " + orderId + "  orderDto - " + orderDto);
		return orderService.updateOrder(orderId, orderDto);
	}

	@DeleteMapping("/orderId/{orderId}")
	public void deleteOrder(@PathVariable Long orderId) {
		System.out.println(" deleteOrder - " + orderId);
		orderService.deleteOrder(orderId);

	}

}
