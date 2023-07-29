package com.shopservice.paymentservice.controller;

import com.shopservice.paymentservice.requests.PaymentRequest;
import com.shopservice.paymentservice.response.PaymentResponse;
import com.shopservice.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {

        log.info("PaymentController | doPayment is called");

        log.info("PaymentController | doPayment | paymentRequest : " + paymentRequest.toString());

        return new ResponseEntity<>(
                paymentService.doPayment(paymentRequest),
                HttpStatus.OK
        );
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable long orderId) {

        log.info("PaymentController | doPayment is called");

        log.info("PaymentController | doPayment | orderId : " + orderId);

        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByOrderId(orderId),
                HttpStatus.OK
        );
    }
}
