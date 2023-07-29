package com.shopservice.paymentservice.repository;

import com.shopservice.paymentservice.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionDetails , Long> {

        Optional<TransactionDetails> findByOrderId(long orderId);
}
