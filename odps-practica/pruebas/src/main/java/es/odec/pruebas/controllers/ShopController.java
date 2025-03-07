package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Shop;
import es.odec.pruebas.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    //Coger datos de las tiendas
    @GetMapping("/list")
    public ResponseEntity<List<Shop>> getShops() {
        return shopService.getShops();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShop(@PathVariable int id) {
        return shopService.getShop(id);
    }

    //Crear una tienda
    @PostMapping("/create")
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        return shopService.createShop(shop);
    }

    //Editar una tienda
    @PutMapping("/edit/{id}")
    public ResponseEntity<Shop> updateShop(@RequestBody Shop shop, @PathVariable int id) {
        return shopService.updateShop(shop, id);
    }

    //Eliminar una tienda
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Shop> deleteShop(@PathVariable int id) {
        return shopService.deleteShop(id);
    }
}
