package es.odec.pruebas.services;

import es.odec.pruebas.models.Product;
import es.odec.pruebas.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepo productRepo;

    // Recoger datos de productos
    @Override
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok().body(productRepo.findAll());
    }

    public ResponseEntity<?> getProductsPaged(Pageable pageable, Specification<Product> spec) {
        try {
            Page<Product> result = productRepo.findAll(spec, pageable);

            if (!result.hasContent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ha ocurrido un error: " + e.getMessage());
        }
    }

    public ResponseEntity<?> productCount(Specification<Product> spec) {
        long result = productRepo.count(spec);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<Product> getProductById(int productId) {
        return ResponseEntity.ok().body(productRepo.findById(productId).get());
    }

    // Crear productos
    @Override
    public ResponseEntity<Product> save(Product product) {
        if (product != null) {
            return ResponseEntity.ok().body(productRepo.save(product));
        } else
            return ResponseEntity.notFound().build();

    }

    // Modificar productos
    @Override
    public ResponseEntity<Product> update(Product product, int id) {

        Product editable = productRepo.findById(id).get();

        if (editable != null) {

            String productName = product.getProductName();
            String productDescription = product.getProductDescription();
            float productPrice = product.getProductPrice();

            if (productName != null) {
                editable.setProductName(productName);
            }
            if (productDescription != null) {
                editable.setProductDescription(productDescription);
            }
            if (productPrice != 0) {
                editable.setProductPrice(productPrice);
            }

            return ResponseEntity.ok().body(productRepo.save(editable));

        } else
            return ResponseEntity.notFound().build();
    }

    // Eliminar productos
    @Override
    public void delete(int id) {
        productRepo.deleteById(id);
    }

}
