package com.product.service;

import com.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product save(Product p);
    Product update(Long id, Product p);
    void delete(Long id);
    Product getById(Long id);
    List<Product> getAll();
}
