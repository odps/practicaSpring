package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Stock;
import es.odec.pruebas.services.StockService;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    //Obtener informacion de stock
    @GetMapping("/pagedList")
    public ResponseEntity<?> getStocksPaged(
            @PageableDefault(sort = "stockQuantity") Pageable pageable,
            @Conjunction({
                    @Or({@Spec(path = "stockProduct", params = "hasProduct", spec = EqualIgnoreCase.class), @Spec(path = "stockProduct", params = "likeProduct", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "stockQuantity", params = "quantityGreater", spec = GreaterThanOrEqual.class), @Spec(path = "stockQuantity", params = "quantityLesser", spec = LessThanOrEqual.class)})
            }) Specification<Stock> spec) {
        return stockService.getStocksPaged(pageable, spec);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getStocks() {
        return stockService.getStocks();
    }

    @GetMapping("/stockCount")
    public ResponseEntity<?> getStockCount(
            @Conjunction({
                    @Or({@Spec(path = "stockProduct", params = "hasProduct", spec = EqualIgnoreCase.class), @Spec(path = "stockProduct", params = "likeProduct", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "stockQuantity", params = "quantityGreater", spec = GreaterThanOrEqual.class), @Spec(path = "stockQuantity", params = "quantityLesser", spec = LessThanOrEqual.class)})
            }) Specification<Stock> spec
    ) {
        return stockService.getStockCount(spec);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStock(@PathVariable int id) {
        return stockService.getStock(id);
    }

    //Crear Roles
    @PostMapping("/create")
    public ResponseEntity<?> createStock(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }

    //Editar roles
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editStock(@PathVariable int id, @RequestBody Stock stock) {
        return stockService.updateStock(stock, id);
    }

    //Eliminar Roles
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
        return stockService.deleteStock(id);
    }


}
