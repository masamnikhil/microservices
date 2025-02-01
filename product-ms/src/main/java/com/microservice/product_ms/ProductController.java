package com.microservice.product_ms;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/saveproduct")
    private ResponseEntity<String> saveProduct(@RequestBody  Product product){
        if(productService.saveProduct(product))
            return ResponseEntity.status(HttpStatus.CREATED).body("Product saved Successfully");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred. please try again");
    }

    @GetMapping("/getproduct/{id}")
    private ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(id));
        }
        catch(RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}")
    private ResponseEntity<List<Product>> getProducts(@PathVariable(name = "name") String name){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts(name));
        }
        catch(RuntimeException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

<<<<<<< HEAD
    @PutMapping("/updateproduct")
    private ResponseEntity<String> updateproduct(@PathVariable("id") Long id){
        return null;
=======
    @GetMapping("/getproducts")
    private ResponseEntity<List<Product>> getAllProducts(@RequestParam(name = "order") String sortOrder,
                                                         @RequestParam(name = "price") String price,
                                                         @RequestParam(name = "name") String name){

        List<Product> productList = productService.getAllProducts(sortOrder, price, name);
        if(productList != null)
            return ResponseEntity.status(HttpStatus.OK).body(productList);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
>>>>>>> 6b6ee8c (sorting added)
    }
}
