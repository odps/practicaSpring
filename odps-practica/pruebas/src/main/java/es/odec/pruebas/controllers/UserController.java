package es.odec.pruebas.controllers;

import es.odec.pruebas.models.User;
import es.odec.pruebas.services.UserService;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Coger datos de los usuarios
    @GetMapping(value = "/list", params = "hasId")
    public ResponseEntity<?> getUsers(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false, defaultValue = "10") int size,
                                      @RequestParam(required = false, defaultValue = "userId") String[] sortParams, //Parametros de ordenacion
                                      @RequestParam(required = false, defaultValue = "ASC") String sort,
                                      @Spec(path = "userId", params = "hasId", spec = In.class) Specification<User> spec
    )
    {
        return userService.getUsers(page, size, sortParams, sort, spec);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    //Crear un usuario
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //Editar un usuario
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id) {
        return userService.updateUser(user, id);
    }

    //Eliminar un usuario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
