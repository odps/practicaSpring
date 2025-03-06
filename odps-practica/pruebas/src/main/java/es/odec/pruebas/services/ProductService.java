package es.odec.pruebas.services;

import es.odec.pruebas.models.Product;
import es.odec.pruebas.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    //Recoger datos de productos
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int productId) {
        return productRepo.findById(productId).get();
    }

    // Crear productos
    public Product save(Product product) {
        return productRepo.save(product);
    }

    // Modificar productos
    public void update(Product product, int id) {

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

            productRepo.save(editable);

        }
    }

    //Eliminar productos
    public void delete(int id) {
        try {
            productRepo.deleteById(id);
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }


}
