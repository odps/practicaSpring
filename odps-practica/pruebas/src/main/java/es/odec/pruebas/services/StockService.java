package es.odec.pruebas.services;

import es.odec.pruebas.models.Stock;
import es.odec.pruebas.repositories.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService implements IStockService {
    @Autowired
    StockRepo stockRepo;

    @Override
    public ResponseEntity<List<Stock>> getStocks() {
        return ResponseEntity.ok().body(stockRepo.findAll());
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
        return null;
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
