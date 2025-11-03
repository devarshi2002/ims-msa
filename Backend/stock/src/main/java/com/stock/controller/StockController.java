package com.stock.controller;

import com.stock.entity.Stock;

import com.stock.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/stocks")

public class StockController {

    @Autowired

    private StockService stockService;

    @PostMapping

    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {

        return ResponseEntity.ok(stockService.createStock(stock));

    }

    @GetMapping

    public ResponseEntity<List<Stock>> getAllStocks() {

        return ResponseEntity.ok(stockService.getAllStocks());

    }

    @GetMapping("/{id}")

    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {

        return stockService.getStockById(id)

                .map(ResponseEntity::ok)

                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")

    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody Stock updatedStock) {

        return stockService.updateStock(id, updatedStock)

                .map(ResponseEntity::ok)

                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {

        return stockService.deleteStock(id)

                ? ResponseEntity.noContent().build()

                : ResponseEntity.notFound().build();

    }

    @GetMapping("/product/{productId}")

    public ResponseEntity<List<Stock>> getStockByProductId(@PathVariable Long productId) {

        return ResponseEntity.ok(stockService.getStockByProductId(productId));

    }

    @PostMapping("/decrease")

    public ResponseEntity<String> decreaseStock(@RequestParam Long productId, @RequestParam Integer quantity) {

        stockService.decreaseStock(productId, quantity);

        return ResponseEntity.ok("Stock decreased successfully for productId: " + productId);

    }

    @PostMapping("/increase")

    public ResponseEntity<String> increaseStock(@RequestParam Long productId, @RequestParam Integer quantity) {

        stockService.increaseStock(productId, quantity);

        return ResponseEntity.ok("Stock increased successfully for productId: " + productId);

    }

}
