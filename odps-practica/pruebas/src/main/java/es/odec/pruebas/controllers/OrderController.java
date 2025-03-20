package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Order;
import es.odec.pruebas.services.OrderService;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // Coger datos de las ordenes
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<Order>> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/pagedList")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getPagedOrders(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @Conjunction({
                    @Or({@Spec(path = "quantity", params = "quantityGreater", spec = GreaterThanOrEqual.class),
                            @Spec(path = "quantity", params = "quantityLess", spec = LessThanOrEqual.class)}),
                    @Or({@Spec(path = "product.productName", params = "hasProduct", spec = EqualIgnoreCase.class),
                            @Spec(path = "product.productName", params = "likeProduct", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "shop.shopName", params = "hasShop", spec = EqualIgnoreCase.class),
                            @Spec(path = "shop.shopName", params = "likeShop", spec = LikeIgnoreCase.class)})
            }) Specification<Order> spec) {
        return orderService.getOrdersPaged(pageable, spec);
    }

    @GetMapping("/orderCount")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> orderCount(
            @Conjunction({
                    @Or({@Spec(path = "quantity", params = "quantityGreater", spec = GreaterThanOrEqual.class),
                            @Spec(path = "quantity", params = "quantityLess", spec = LessThanOrEqual.class)}),
                    @Or({@Spec(path = "product.productName", params = "hasProduct", spec = EqualIgnoreCase.class),
                            @Spec(path = "product.productName", params = "likeProduct", spec = LikeIgnoreCase.class)})
            }) Specification<Order> spec) {
        return orderService.orderCount(spec);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Order> getOrder(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    // Crear una order
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // Editar uan orden
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable int id) {
        return orderService.updateOrder(order, id);
    }

    // Borrar orden
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Order> deleteOrder(@PathVariable int id) {
        return orderService.deleteOrder(id);
    }
}
