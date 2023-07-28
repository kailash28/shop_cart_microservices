package com.shopservice.productservice.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private String productName;
    private long quantity;
    private long price;
}
