package com.microservice.product_ms;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
<<<<<<< HEAD
class Product {
=======
public class Product {
>>>>>>> 6b6ee8c (sorting added)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String company;
    @Column(nullable = false)
    private String colour;
    @Column(nullable = false)
    private Double averageRating;

}
