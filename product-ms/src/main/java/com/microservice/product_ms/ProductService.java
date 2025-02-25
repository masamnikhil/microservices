package com.microservice.product_ms;

import com.microservice.product_ms.dto.ReviewMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ProductService {

    @Transactional
    public boolean saveProduct(Product product);
    public Product getProduct(Long id);
    public List<Product> getProducts(String name);
    @Transactional
    public boolean UpdateProduct(Long id);
    @Transactional
    public void updateProductRating(ReviewMessage reviewMessage);

    public List<Product> getAllProducts(String order, String price, String name);

}
