package es.odec.pruebas.services;

import es.odec.pruebas.models.Order;
import es.odec.pruebas.models.User;
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
        return ResponseEntity.ok().body(orderRepo.findAll());
    }

    @Override
    public ResponseEntity<Order> getOrder(int id) {
        if (!orderRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(orderRepo.findById(id).get());
    }

    @Override
    public ResponseEntity<Order> createOrder(Order order) {
        if (order == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(orderRepo.save(order));
    }

    @Override
    public ResponseEntity<Order> updateOrder(Order oldOrder, int id) {
        if (!orderRepo.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        Order orderNew = orderRepo.findById(id).get();
        orderNew.setQuantity(oldOrder.getQuantity());
        orderNew.setProduct(oldOrder.getProduct());
        orderNew.setShop(oldOrder.getShop());
        orderNew.setInvoices(oldOrder.getInvoices());

        Order saved = orderRepo.save(orderNew);
        return ResponseEntity.ok().body(saved);
    }

    @Override
    public ResponseEntity<Order> deleteOrder(int id) {
        if (!orderRepo.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        orderRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
