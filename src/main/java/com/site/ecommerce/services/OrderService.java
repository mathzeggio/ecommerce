package com.site.ecommerce.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.site.ecommerce.repositories.OrderRepository;
import com.site.ecommerce.entities.Order;

@Service
public class OrderService {

    @Autowired
    private OrderRepository OrderRepository;

    public List<Order> getAllOrders() {
        return OrderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return OrderRepository.findById(id);
    }

    public Order addOrder(Order Order) {
        return OrderRepository.save(Order);
    }

    public Order updateOrder(String id, Order Order) {
        if (OrderRepository.existsById(id)) {
            Order.setId(id);
            return OrderRepository.save(Order);
        } else {
            throw new IllegalArgumentException("Order not found with ID: " + id);
        }
    }

    public void deleteOrder(String id) {
        OrderRepository.deleteById(id);
    }
}
