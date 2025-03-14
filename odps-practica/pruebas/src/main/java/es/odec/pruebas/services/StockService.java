package es.odec.pruebas.services;

import es.odec.pruebas.models.Stock;
import es.odec.pruebas.repositories.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService implements IStockService {
    @Autowired
    StockRepo stockRepo;

    public ResponseEntity<?> getStocksPaged(Pageable pageable, Specification<Stock> spec) {
        Page<Stock> result = stockRepo.findAll(spec, pageable);
        if (!result.hasContent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<Stock>> getStocks() {
        return ResponseEntity.ok(stockRepo.findAll());
    }

    public ResponseEntity<?> getStockCount(Specification<Stock> spec) {
        return ResponseEntity.ok(stockRepo.count(spec));
    }

    @Override
    public ResponseEntity<Stock> getStock(int id) {
        if (!stockRepo.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok().body(stockRepo.findById(id).get());
    }

    @Override
    public ResponseEntity<Stock> createStock(Stock stock) {
        if (stock == null) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok().body(stockRepo.save(stock));
    }

    @Override
    public ResponseEntity<Stock> updateStock(Stock stock, int id) {

        if (!stockRepo.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        Stock stockNew = stockRepo.findById(id).get();
        stockNew.setStockOwner(stock.getStockOwner());
        stockNew.setStockProduct(stock.getStockProduct());
        stockNew.setStockQuantity(stock.getStockQuantity());
        stockNew.setOrders(stock.getOrders());

        Stock saved = stockRepo.save(stockNew);
        return ResponseEntity.ok().body(saved);
    }

    @Override
    public ResponseEntity<Stock> deleteStock(int id) {
        if (!stockRepo.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        stockRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
