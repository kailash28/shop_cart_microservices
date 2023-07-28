package com.shopservice.productservice.service;

import com.shopservice.productservice.requests.ProductRequest;
import com.shopservice.productservice.response.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);
    ProductResponse getProductById(long productId);
    void reduceQuantity(long productId , long quntity);
    public void deleteProduct(long productId);

}
