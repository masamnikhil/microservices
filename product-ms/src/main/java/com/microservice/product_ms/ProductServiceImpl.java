package com.microservice.product_ms;

import com.microservice.product_ms.clients.ReviewClient;
import com.microservice.product_ms.dto.ReviewMessage;
import jakarta.ws.rs.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springframework.stereotype.Component;
import java.util.List;
=======
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
>>>>>>> 6b6ee8c (sorting added)


@Component
@RequiredArgsConstructor
<<<<<<< HEAD
public class ProductServiceImpl implements ProductService{
=======
public class ProductServiceImpl implements ProductService {

>>>>>>> 6b6ee8c (sorting added)

    private final ProductRepository productRepository;

    private final ReviewClient reviewClient;
<<<<<<< HEAD
=======

>>>>>>> 6b6ee8c (sorting added)
    @Override
    public boolean saveProduct(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public Product getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException());
        product.setAverageRating(product.getAverageRating());
        return product;
    }

    @Override
    public List<Product> getProducts(String name) {
        List<Product> products = productRepository.findAllByName(name).orElseThrow(() -> new RuntimeException());

<<<<<<< HEAD
            return products;
=======
        return products;
>>>>>>> 6b6ee8c (sorting added)

    }

    @Override
    public boolean UpdateProduct(Long id) {
        return false;
    }

    @Override
    public void updateProductRating(@NonNull ReviewMessage reviewMessage) {
        Product product = productRepository.findById(reviewMessage.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found with id" + reviewMessage.getProductId()));

<<<<<<< HEAD
             double averageRating = reviewClient.getAverageRatingForProduct(reviewMessage.getProductId());
             product.setAverageRating(averageRating);
             productRepository.save(product);
    }
}
=======
        double averageRating = reviewClient.getAverageRatingForProduct(reviewMessage.getProductId());
        product.setAverageRating(averageRating);
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(String sortOrder, String price, String name) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(String.valueOf(price)).ascending() : Sort.by(price).ascending();

        Optional<List<Product>> products = productRepository.findByNameContaining(name, sort);

        if(products.isPresent())
            return products.get();

        return null;
    }
}

>>>>>>> 6b6ee8c (sorting added)
