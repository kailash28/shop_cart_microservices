package com.shopservice.paymentservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private long paymentId;
    private String status;
    private String referenceNumber;
    private long amount;
    private long orderId;
    private Instant time;
    private String paymentMethod;
}
