package com.shopservice.paymentservice.service;

import com.shopservice.paymentservice.requests.PaymentRequest;
import com.shopservice.paymentservice.response.PaymentResponse;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
