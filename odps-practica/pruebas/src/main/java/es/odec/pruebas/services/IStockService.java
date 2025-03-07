package es.odec.pruebas.services;

import es.odec.pruebas.models.Stock;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStockService {
    ResponseEntity<List<Stock>> getStocks();

    ResponseEntity<Stock> getStock(int id);

    ResponseEntity<Stock> createStock(Stock stock);

    ResponseEntity<Stock> updateStock(Stock stock, int id);

    ResponseEntity<Stock> deleteStock(int id);

}
