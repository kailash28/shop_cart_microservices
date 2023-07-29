package com.shopservice.orderservice.serviceimpl;

import com.shopservice.orderservice.entity.Order;
import com.shopservice.orderservice.exceptions.OrderServiceCustomException;
import com.shopservice.orderservice.repository.OrderRepository;
import com.shopservice.orderservice.requests.OrderRequest;
import com.shopservice.orderservice.requests.PaymentRequest;
import com.shopservice.orderservice.response.OrderResponse;
import com.shopservice.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    //

    @Override
    public long placeOrder(OrderRequest orderRequest) {

        log.info("OrderServiceImpl | placeOrder is called");


        log.info("OrderServiceImpl | placeOrder | Placing Order Request orderRequest : " + orderRequest.toString());

        log.info("OrderServiceImpl | placeOrder | Creating Order with Status CREATED");
        Order order = Order.builder()
                .price(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        order = orderRepository.save(order);

        log.info("OrderServiceImpl | placeOrder | Calling Payment Service to complete the payment");

        PaymentRequest paymentRequest
                = PaymentRequest.builder()
                .orderId(order.getOrderId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus = null;

        try {
            log.info("OrderServiceImpl | placeOrder | Payment done Successfully. Changing the Oder status to PLACED");
            orderStatus = "PLACED";
        } catch (Exception e) {
            log.error("OrderServiceImpl | placeOrder | Error occurred in payment. Changing order status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);

        orderRepository.save(order);

        log.info("OrderServiceImpl | placeOrder | Order Places successfully with Order Id: {}", order.getOrderId());

        return order.getOrderId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        return null;
    }

}
