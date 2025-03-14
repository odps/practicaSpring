package es.odec.pruebas.services;

import es.odec.pruebas.models.Shop;
import es.odec.pruebas.repositories.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public ResponseEntity<?> getShopsPaged(Pageable pageable, Specification<Shop> spec) {
        try {
            Page<Shop> result = shopRepo.findAll(spec, pageable);

            if (!result.hasContent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ha ocurrido un error: " + e.getMessage());
        }
    }

    public ResponseEntity<?> shopCount(Specification<Shop> spec) {
        long result = shopRepo.count(spec);
        return ResponseEntity.ok().body(result);
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
