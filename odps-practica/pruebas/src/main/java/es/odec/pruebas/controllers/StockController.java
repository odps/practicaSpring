package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Stock;
import es.odec.pruebas.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    //Obtener informacion de stock
    @GetMapping("/list")
    public ResponseEntity<List<Stock>> getStocks() {
        return stockService.getStocks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable int id) {
        return stockService.getStock(id);
    }

    //Crear Roles
    @PostMapping("/create")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }

    //Editar roles
    @PutMapping("/edit/{id}")
    public ResponseEntity<Stock> editStock(@PathVariable int id, @RequestBody Stock stock) {
        return stockService.updateStock(stock, id);
    }

    //Eliminar Roles
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Stock> deleteRole(@PathVariable int id) {
        return stockService.deleteStock(id);
    }


}
