package es.odec.pruebas.services;

import es.odec.pruebas.models.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOrderService {
    ResponseEntity<List<Order>> getOrders();

    ResponseEntity<Order> getOrder(int id);

    ResponseEntity<Order> createOrder();

    ResponseEntity<Order> updateOrder(Order order);

    ResponseEntity<Order> deleteOrder(int id);

}
