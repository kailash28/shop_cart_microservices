package com.shopservice.productservice.serviceimpl;

import com.shopservice.productservice.entity.Product;
import com.shopservice.productservice.exceptions.ProductServiceCustomException;
import com.shopservice.productservice.repository.ProductRepository;
import com.shopservice.productservice.requests.ProductRequest;
import com.shopservice.productservice.response.ProductResponse;
import com.shopservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
       log.info("ProductServiceImpl called | adding Product ");
       Product product = Product.builder().
               productName(productRequest.getProductName())
               .price(productRequest.getPrice())
               .quantity(productRequest.getQuantity())
               .build();

        product = productRepository.save(product);
        log.info("ProductServiceImpl called | added Product | Product ID :: {}" ,product.getProductId());
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("ProductServiceImpl called | getting Product by productID :: {}" ,productId);
        Product product = productRepository.findById(productId)
                .orElseThrow( () -> new ProductServiceCustomException("Product with ID not found","PRODUCT_NOT_FOUND"));

        ProductResponse productResponse = new ProductResponse();
        productResponse = copyProductToProductResponse(product , productResponse);

        log.info("ProductServiceImpl called | getting Product by productID :: {} completed" ,productId);
        return productResponse;
    }

    private ProductResponse copyProductToProductResponse(Product product, ProductResponse productResponse) {
        productResponse.setProductName(product.getProductName());
        productResponse.setProductId(product.getProductId());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity,productId);

        Product product
                = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product with given Id not found",
                        "PRODUCT_NOT_FOUND"
                ));

        if(product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product Quantity updated Successfully");
    }

    @Override
    public void deleteProduct(long productId) {
        log.info("Product id: {}", productId);

        if (!productRepository.existsById(productId)) {
            log.info("Im in this loop {}", !productRepository.existsById(productId));
            throw new ProductServiceCustomException(
                    "Product with given with Id: " + productId + " not found:",
                    "PRODUCT_NOT_FOUND");
        }
        log.info("Deleting Product with id: {}", productId);
        productRepository.deleteById(productId);
        log.info("Deleted Product with id: {}", productId);
    }
}
