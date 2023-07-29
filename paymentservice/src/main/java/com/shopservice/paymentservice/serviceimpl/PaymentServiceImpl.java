package com.shopservice.paymentservice.serviceimpl;

import com.shopservice.paymentservice.entity.TransactionDetails;
import com.shopservice.paymentservice.exceptions.PaymentServiceCustomException;
import com.shopservice.paymentservice.repository.TransactionRepository;
import com.shopservice.paymentservice.requests.PaymentRequest;
import com.shopservice.paymentservice.response.PaymentResponse;
import com.shopservice.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {


    private final TransactionRepository transactionRepository;
    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("PaymentServiceImpl | doPayment is called ");
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .amount(paymentRequest.getAmount())
                .orderId(paymentRequest.getOrderId())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .timeDate(Instant.now())
                .paymentStatus("SUCCESS")
                .build();
        transactionDetails = transactionRepository.save(transactionDetails);

        log.info("PaymentServiceImpl | doPayment is finished | Transaction ID : {} ",transactionDetails.getTransactionId());

        return transactionDetails.getTransactionId();

    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        log.info("PaymentServiceImpl | getPaymentDetailsByOrderId is called");

        log.info("PaymentServiceImpl | getPaymentDetailsByOrderId | Getting payment details for the Order Id: {}", orderId);

        TransactionDetails transactionDetails
                = transactionRepository.findByOrderId(orderId)
                .orElseThrow(() -> new PaymentServiceCustomException(
                        "TransactionDetails with given id not found",
                        "TRANSACTION_NOT_FOUND"));

        PaymentResponse paymentResponse
                = PaymentResponse.builder()
                .paymentId(transactionDetails.getTransactionId())
                .paymentMethod(transactionDetails.getPaymentMethod())
                .time(transactionDetails.getTimeDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .build();

        log.info("PaymentServiceImpl | getPaymentDetailsByOrderId | paymentResponse: {}", paymentResponse.toString());

        return paymentResponse;

    }
}
