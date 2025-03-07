package es.odec.pruebas.services;

import es.odec.pruebas.models.Order;
import es.odec.pruebas.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepo orderRepo;

    @Override
    public ResponseEntity<List<Order>> getOrders() {
        return null;
    }

    @Override
    public ResponseEntity<Order> getOrder(int id) {
        return null;
    }

    @Override
    public ResponseEntity<Order> createOrder() {
        return null;
    }

    @Override
    public ResponseEntity<Order> updateOrder(Order order) {
        return null;
    }

    @Override
    public ResponseEntity<Order> deleteOrder(int id) {
        return null;
    }
}
