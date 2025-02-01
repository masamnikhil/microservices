package com.microservice.product_ms;

<<<<<<< HEAD
=======
import org.springframework.data.domain.Sort;
>>>>>>> 6b6ee8c (sorting added)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   Optional<List<Product>> findAllByName(String name);
<<<<<<< HEAD
=======


    Optional<List<Product>> findByNameContaining(String name, Sort sort);
>>>>>>> 6b6ee8c (sorting added)
}
