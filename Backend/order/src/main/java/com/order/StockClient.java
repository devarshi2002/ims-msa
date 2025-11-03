package com.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock") // Eureka service name, no URL needed
public interface StockClient {
    @PostMapping("/api/stocks/decrease")
    String decreaseStock(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity);
}