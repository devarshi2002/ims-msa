package com.stock.service;

import com.stock.entity.Stock;
import com.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }

    public Optional<Stock> updateStock(Long id, Stock updatedStock) {
        return stockRepository.findById(id).map(existingStock -> {
            existingStock.setQuantity(updatedStock.getQuantity());
            existingStock.setLocation(updatedStock.getLocation());
            existingStock.setProductId(updatedStock.getProductId());
            return stockRepository.save(existingStock);
        });
    }

    public boolean deleteStock(Long id) {
        return stockRepository.findById(id).map(stock -> {
            stockRepository.delete(stock);
            return true;
        }).orElse(false);
    }

    // Add missing method
    public List<Stock> getStockByProductId(Long productId) {
        return stockRepository.findByProductId(productId);
    }

    // Add missing method
    public void decreaseStock(Long productId, Integer quantity) {
        List<Stock> stocks = stockRepository.findByProductId(productId);
        if (stocks.isEmpty()) {
            throw new RuntimeException("Product not found: " + productId);
        }
        Stock stock = stocks.get(0); // Assume one stock entry per productId
        if (stock.getQuantity() >= quantity) {
            stock.setQuantity(stock.getQuantity() - quantity);
            stockRepository.save(stock);
        } else {
            throw new RuntimeException("Insufficient stock for productId: " + productId);
        }
    }

    public boolean increaseStock(Long productId, Integer quantity) {
        Optional<Stock> optionalStock = stockRepository.findById(productId);
        if (optionalStock.isPresent()) {
            Stock stock = optionalStock.get();
            stock.setQuantity(stock.getQuantity() + quantity);
            stockRepository.save(stock);
            return true;
        } else {
            throw new RuntimeException("Product not found: " + productId);
        }
    }
}