package com.shopservice.paymentservice.exceptions;

import lombok.Data;

@Data
public class PaymentServiceCustomException extends RuntimeException{
    private String errorCode;
    public PaymentServiceCustomException(String errorCode , String errorMessage ){
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
