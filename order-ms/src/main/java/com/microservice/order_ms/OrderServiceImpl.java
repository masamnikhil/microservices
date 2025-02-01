package com.microservice.order_ms;

import com.microservice.order_ms.clients.UserClient;
import com.microservice.order_ms.external.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final UserClient userClient;
    @Override
    public boolean saveOrder(Order order, String username) {
        User user = userClient.getUser(username);
            order.setUserId(user.getUserid());
            order.setStatus("Confirmed");
            orderRepository.save(order);
            return true;

    }

    @Override
    public List<Order> getOrders(Long UserId) {
        Optional<List<Order>> orders = orderRepository.findAllByUserId(UserId);
        if(orders.isEmpty())
            throw new RuntimeException("No orders found");

        return orders.get();
    }

    @Override
    public boolean updateOrder(Long orderId) {
        Optional<Order> order= orderRepository.findById(orderId);
        if(order.isPresent()){
            Order o = order.get();
            o.setStatus("Delivered");
            orderRepository.save(o);
            return true;
        }
        return false;
    }
}
