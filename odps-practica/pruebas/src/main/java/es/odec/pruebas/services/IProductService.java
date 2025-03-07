package es.odec.pruebas.services;

import es.odec.pruebas.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    public ResponseEntity<List<Product>> getProducts();

    public ResponseEntity<Product> getProductById(int productId);

    public ResponseEntity<Product> save(Product product);

    public ResponseEntity<Product> update(Product product, int id);

    public void delete(int id);
}
