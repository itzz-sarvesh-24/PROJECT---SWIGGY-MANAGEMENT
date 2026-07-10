package com.swiggy.service;

import com.swiggy.entity.Item;
import com.swiggy.entity.Order;
import com.swiggy.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(Order order) {

        double total = 0;

        for (Item item : order.getItems()) {
            total += item.getPrice() * item.getQuantity();
        }

        order.setTotalAmount(total);

        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteOrder(int id) {

        if(repository.existsById(id)) {
            repository.deleteById(id);
            return "Order Deleted Successfully";
        }

        return "Order Not Found";
    }
}