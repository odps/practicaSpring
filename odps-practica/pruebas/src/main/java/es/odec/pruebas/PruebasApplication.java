package es.odec.pruebas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class PruebasApplication {
    public static void main(String[] args) {
        SpringApplication.run(PruebasApplication.class, args);
        System.out.println("Se ha iniciado el servidor correctamente.");
    }
}
