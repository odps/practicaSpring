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
        return null;
    }

    @Override
    public ResponseEntity<Stock> getStock(int id) {
        return null;
    }

    @Override
    public ResponseEntity<Stock> createStock() {
        return null;
    }

    @Override
    public ResponseEntity<Stock> updateStock(Stock stock) {
        return null;
    }

    @Override
    public ResponseEntity<Stock> deleteStock(int id) {
        return null;
    }
}
