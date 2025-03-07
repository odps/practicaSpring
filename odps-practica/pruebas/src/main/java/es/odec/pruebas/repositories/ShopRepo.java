package es.odec.pruebas.repositories;

import es.odec.pruebas.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepo extends JpaRepository<Shop, Integer> {
}
