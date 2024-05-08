package com.site.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.site.ecommerce.services.OrderService;
import com.site.ecommerce.entities.Order;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService OrderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> Orders = OrderService.getAllOrders();
        return ResponseEntity.ok(Orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return OrderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order addOrder(@RequestBody Order Order) {
        System.out.println("Order");
        return OrderService.addOrder(Order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order Order) {
        Order updatedOrder = OrderService.updateOrder(id, Order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String id) {
        OrderService.deleteOrder(id);
    }
}
