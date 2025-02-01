package com.microservice.order_ms;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public boolean saveOrder(Order order, String username);
    public List<Order> getOrders(Long userId);
    public boolean updateOrder(Long orderId);
}
