package es.odec.pruebas.controllers;

import es.odec.pruebas.models.User;
import es.odec.pruebas.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/userid/{id}")
    ResponseEntity<?> getUserById(@PathVariable int id) {
        User user = userRepo.findUserByIdQuery(id);
        return ResponseEntity.ok(user);
    }


}
