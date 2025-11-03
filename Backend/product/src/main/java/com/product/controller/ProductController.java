package com.product.controller;

import com.product.entity.Product;
import com.product.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service){ this.service = service; }

    @PostMapping
    public Product create(@RequestBody Product p){ return service.save(p); }

    @GetMapping
    public List<Product> all(){ return service.getAll(); }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id){ return service.getById(id); }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product p){ return service.update(id, p); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){ service.delete(id); }
}

