package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Order;
import es.odec.pruebas.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //Coger datos de las ordenes
    @GetMapping("/list")
    public ResponseEntity<List<Order>> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    //Crear una order
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    //Editar uan orden
    @PutMapping("/edit/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable int id) {
        return orderService.updateOrder(order, id);
    }

    //Borrar orden
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable int id) {
        return orderService.deleteOrder(id);
    }
}
