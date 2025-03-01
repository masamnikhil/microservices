package com.microservice.product_ms;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   Optional<List<Product>> findAllByName(String name);

   Optional<List<Product>> findByNameContaining(String name, Sort sort);

}
