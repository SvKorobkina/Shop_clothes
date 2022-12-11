package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.enumm.Status;
import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;


    @Transactional
    public Order getAllOrders(int id, Order order) {
        order.setId(id);
        orderRepository.save(order);
        return order;
    }

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Данный метод позволяет вернуть все заказы
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Данный метод позволяет обновить данные пользователя
    @Transactional
    public void updateOrder(int id, Order order) {
        order.setId(id);
        orderRepository.save(order);

    }

    @Transactional
    public void updateOrderStatus(Order order) {
        orderRepository.save(order);
    }

    //    @Transactional
    public Order getOrderById(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    // --------------------------
//    public List<Order> getOrderByFindingWith(String ending_with) {
//        List<Order> orderList = orderRepository.findByLast4(ending_with);
//        return orderList;
//    }

//    public List<Order> findByLast(String ending_with) {
//        List<Order> orderList = orderRepository.findByLast(ending_with);
//        return orderList;
//    }
}




