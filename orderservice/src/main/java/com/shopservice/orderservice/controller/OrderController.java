package com.shopservice.orderservice.controller;

import com.shopservice.orderservice.requests.OrderRequest;
import com.shopservice.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/placeorder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {

        log.info("OrderController | placeOrder is called");

        log.info("OrderController | placeOrder | orderRequest: {}", orderRequest.toString());

        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order Id: {}", orderId);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
}
