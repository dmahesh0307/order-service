package com.example.demo;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;


    @BeforeEach
    public void setup(){
        mockStatic(OrderDataMapper.class);


    }

    @Test
    public void saveEmplyeeTest() {
		OrderDto expected = getOrderDtoObject("order123", "goods", "rice", 20, 2000.0);
		Order order =new Order();
        Mockito.when(OrderDataMapper.generateOrderObject(expected)).thenReturn(order);
    	OrderDto output=orderServiceImpl.addOrder(expected);
    }
    
    private OrderDto getOrderDtoObject( String orderId, String orderType, String product, int quantity,
			double totalAmount) {
		OrderDto orderDto = new OrderDto();
		orderDto.setOrderId(orderId);
		orderDto.setOrderType(orderType);
		orderDto.setProduct(product);
		orderDto.setQuantity(quantity);
		orderDto.setTotalAmount(totalAmount);
		return orderDto;
	}
}
