package es.odec.pruebas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import es.odec.pruebas.models.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
}
