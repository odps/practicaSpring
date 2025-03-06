package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Product;
import es.odec.pruebas.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Rutas para obtener productos
    @GetMapping("/list")
    public ResponseEntity<List<Product>> productList() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> productById(@PathVariable int id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    //Ruta para la creacion de productos
    @PostMapping("/create")
    public void createProduct(@RequestBody Product product) {
        try {
            productService.save(product);
        } catch (Exception e) {
            System.out.println("=== Ha ocurrido un error: " + e.getMessage());
        }
    }

    //Ruta para la modificacion de productos
    @PutMapping("/edit/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable int id) {
        try {
            productService.update(product, id);
        } catch (Exception e) {
            System.out.println("=== Ha ocurrido un error: " + e.getMessage());
        }
    }

    //Ruta para la eliminacion de productos
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        try {
            productService.delete(id);
        } catch (Exception e) {
            System.out.println("=== Ha ocurrido un error: " + e.getMessage());
        }
    }
}
