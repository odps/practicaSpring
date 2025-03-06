package es.odec.pruebas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebasApplication {
    public static void main(String[] args) {
        SpringApplication.run(PruebasApplication.class, args);
        System.out.println("=== Se ha iniciado el servidor correctamente.");
    }
}
