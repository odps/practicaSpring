package es.odec.pruebas.services;

import es.odec.pruebas.models.Shop;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IShopService {
    ResponseEntity<List<Shop>> getShops();

    ResponseEntity<Shop> getShop(int id);

    ResponseEntity<Shop> createShop(Shop shop);

    ResponseEntity<Shop> updateShop(Shop shop, int id);

    ResponseEntity<Shop> deleteShop(int id);

}
