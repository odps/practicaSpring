package es.odec.pruebas.services;

import es.odec.pruebas.models.Shop;
import es.odec.pruebas.repositories.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService implements IShopService {
    @Autowired
    private ShopRepo shopRepo;

    @Override
    public ResponseEntity<List<Shop>> getShops() {
        return ResponseEntity.ok(shopRepo.findAll());
    }

    @Override
    public ResponseEntity<Shop> getShop(int id) {
        if (!shopRepo.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shopRepo.findById(id).get());
    }

    @Override
    public ResponseEntity<Shop> createShop(Shop shop) {
        if (shop == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(shopRepo.save(shop));
    }

    @Override
    public ResponseEntity<Shop> updateShop(Shop shop, int id) {

        if (!shopRepo.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        Shop shopNew = shopRepo.findById(id).get();
        shopNew.setShopName(shop.getShopName());
        shopNew.setShopOwner(shop.getShopOwner());
        shopNew.setShopAdress(shop.getShopAdress());
        shopNew.setShopEmail(shop.getShopEmail());
        shopNew.setShopPhone(shop.getShopPhone());
        shopNew.setStocks(shop.getStocks());
        shopNew.setOrders(shop.getOrders());

        Shop saved = shopRepo.save(shopNew);
        return ResponseEntity.ok().body(saved);
    }

    @Override
    public ResponseEntity<Shop> deleteShop(int id) {
        if (!shopRepo.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        shopRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
