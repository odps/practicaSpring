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

    public List<Product> getProducts(){
        return productRepo.findAll();
    }

    public Product getProductById(int productId){
        return productRepo.findById(productId).get();
    }
}
