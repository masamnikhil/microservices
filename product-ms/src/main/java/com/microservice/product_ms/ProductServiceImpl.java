package com.microservice.product_ms;

import com.microservice.product_ms.clients.ReviewClient;
import com.microservice.product_ms.dto.ReviewMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class ProductServiceImpl implements ProductService {

       private final ProductRepository productRepository;

       private final ReviewClient reviewClient;

    @Override
    public boolean saveProduct(Product product) {
         productRepository.save(product);
         return true;
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public List<Product> getProducts(String name) {
        return List.of();
    }

    @Override
    public boolean UpdateProduct(Long id) {
        return false;
    }

    @Override
    public void updateProductRating(ReviewMessage reviewMessage) {
        Product product = productRepository.findById(reviewMessage.getProductId()).orElseThrow(
                () -> new EntityNotFoundException());

        double averageRating = reviewClient.getAverageRatingForProduct(product.getId());
        product.setAverageRating(averageRating);
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(String order, String price, String name) {
        return List.of();
    }
}
