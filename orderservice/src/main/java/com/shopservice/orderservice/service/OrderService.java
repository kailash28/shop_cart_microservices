package com.shopservice.orderservice.service;

import com.shopservice.orderservice.requests.OrderRequest;
import com.shopservice.orderservice.response.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
