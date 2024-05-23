package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
@ExtendWith(SpringExtension.class)
public class OrderControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	OrderService orderService;

	@Test
	void testOrderGetMthod() throws Exception {
		// Approach 1
		OrderDto expected = getOrderDtoObject(1l, "order123", "goods", "rice", 20, 2000.0);
		when(orderService.getOrder(1l)).thenReturn(expected);
		mockMvc.perform(get("/orders/orderId/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1l))
				.andExpect(jsonPath("$.orderId").value("order123")).andExpect(jsonPath("$.orderType").value("goods"))
				.andExpect(jsonPath("$.product").value("rice")).andExpect(jsonPath("$.quantity").value(20))
				.andExpect(jsonPath("$.totalAmount").value(2000.0));

		// Approach 2

		MvcResult mvcResult = mockMvc.perform(get("/orders/orderId/1")).andExpect(status().isOk()).andReturn();
		String stringData = mvcResult.getResponse().getContentAsString();
		OrderDto orderDto = new ObjectMapper().readValue(stringData, OrderDto.class);
		assertThat(orderDto.getOrderId()).isEqualTo(expected.getOrderId());
		assertThat(orderDto.getProduct()).isEqualTo(expected.getProduct());
		assertThat(orderDto.getQuantity()).isEqualTo(expected.getQuantity());
		assertThat(orderDto.getTotalAmount()).isEqualTo(expected.getTotalAmount());

	}

	@Test
	void testOrderPostMthod() throws Exception {
		OrderDto expected = getOrderDtoObject("order123", "goods", "rice", 20, 2000.0);
		String requestBody = new ObjectMapper().writeValueAsString(expected);
		Mockito.when(orderService.addOrder(expected)).thenReturn(expected);
		mockMvc
				.perform(post("/orders").content(requestBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private OrderDto getOrderDtoObject(Long id, String orderId, String orderType, String product, int quantity,
			double totalAmount) {
		OrderDto orderDto = new OrderDto(id, orderId, orderType, product, quantity, totalAmount);
		return orderDto;
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
