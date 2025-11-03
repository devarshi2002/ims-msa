package com.order.service;
import com.order.StockClient;
import com.order.entity.Order;
import com.order.repository.OrderRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StockClient stockClient;

    public Order placeOrder(Order order) {
        if (order.getProductId() == null || order.getQuantity() == null || order.getQuantity() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid productId or quantity");
        }

        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        try {
            stockClient.decreaseStock(order.getProductId(), order.getQuantity());
        } catch (FeignException e) {
            String message = e.getMessage();
            if (e.status() == 404) {
                message = "Stock service endpoint not found. Ensure Stock Service is running.";
            } else if (e.status() == 400) {
                message = "Stock update failed: Likely insufficient stock or product not found.";

            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock update failed: " + message, e);
        }
        return orderRepository.save(order);
    }

    // Other methods unchanged
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        existingOrder.setProductId(updatedOrder.getProductId());
        existingOrder.setQuantity(updatedOrder.getQuantity());
        existingOrder.setStatus(updatedOrder.getStatus());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        orderRepository.deleteById(id);
    }
}
