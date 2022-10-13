package com.mmit.controller.request;

import java.util.List;

public class OrderRequestData {
		
	private OrderReceiverData receiver;
	private List<OrderProductData> orderItems;

	

	public OrderReceiverData getReceiver() {
		return receiver;
	}

	public List<OrderProductData> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderProductData> orderItems) {
		this.orderItems = orderItems;
	}

	public void setReceiver(OrderReceiverData receiver) {
		this.receiver = receiver;
	}
	
}
