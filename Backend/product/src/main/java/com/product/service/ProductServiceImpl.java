package com.product.service;

import com.product.entity.Product;
import com.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;
    public ProductServiceImpl(ProductRepository repo){ this.repo = repo; }

    @Override public Product save(Product p){ return repo.save(p); }
    @Override public Product update(Long id, Product p){
        Product existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        existing.setName(p.getName());
        existing.setCategory(p.getCategory());
        existing.setPrice(p.getPrice());
        existing.setDescription(p.getDescription());
        return repo.save(existing);
    }
    @Override public void delete(Long id) { repo.deleteById(id); }
    @Override public Product getById(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Not found")); }
    @Override public List<Product> getAll(){ return repo.findAll(); }

}
