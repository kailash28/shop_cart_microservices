package com.shopservice.orderservice.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "Order_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    @Column(name = "ORDER_DATE")
    private Instant orderDate;
    @Column(name = "PRICE")
    private long price;
    @Column(name = "PRODUCT_ID")
    private long productId;
    @Column(name = "QUANTITY")
    private long quantity;
    @Column(name = "ORDER_STATUS")
    private String orderStatus;

}
