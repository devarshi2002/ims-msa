package com.order.controller;

import com.order.entity.Order;

import com.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3001") // Allow frontend requests

@RestController

@RequestMapping("/api/orders")

public class OrderController {

    @Autowired

    private OrderService orderService;

    // Create Order

    @PostMapping

    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {

        return ResponseEntity.status(201).body(orderService.placeOrder(order));

    }

    // Get All Orders

    @GetMapping

    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(orderService.getAllOrders());

    }

    // Get Order by ID

    @GetMapping("/{id}")

    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {

        return ResponseEntity.ok(orderService.getOrderById(id));

    }

    // Update Order

    @PutMapping("/{id}")

    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {

        return ResponseEntity.ok(orderService.updateOrder(id, order));

    }

    // Delete Order

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {

        orderService.deleteOrder(id);

        return ResponseEntity.noContent().build();

    }

}
