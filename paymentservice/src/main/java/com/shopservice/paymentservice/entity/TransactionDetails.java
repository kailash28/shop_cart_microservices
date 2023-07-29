package com.shopservice.paymentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;
    @Column(name = "ORDER_ID")
    private long orderId;
    @Column(name = "REFERENCE_NUMBER")
    private String referenceNumber;
    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;
    @Column(name = "AMOUNT")
    private long amount;
    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;
    @Column(name ="TIME_DATE")
    private Instant timeDate;

}
