package es.odec.pruebas.controllers;

import es.odec.pruebas.models.User;
import es.odec.pruebas.services.UserService;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //Coger datos de los usuarios
    @GetMapping("/pagedList")
    public ResponseEntity<?> getPagedUsers
    (@PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
     @Conjunction({
             @Or({@Spec(path = "firstName", params = "hasName", spec = EqualIgnoreCase.class), @Spec(path = "firstName", params = "likeName", spec = LikeIgnoreCase.class)}),
             @Or({@Spec(path = "lastName", params = "hasLastName", spec = EqualIgnoreCase.class), @Spec(path = "lastName", params = "likeLastName", spec = LikeIgnoreCase.class)})
     }) Specification<User> spec
    ) {
        return userService.getUsers(pageable, spec);
    }

    @GetMapping("/userCount")
    public ResponseEntity<?> userCount(
            @Conjunction({
                    @Or({@Spec(path = "firstName", params = "hasName", spec = EqualIgnoreCase.class), @Spec(path = "firstName", params = "likeName", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "lastName", params = "hasLastName", spec = EqualIgnoreCase.class), @Spec(path = "lastName", params = "likeLastName", spec = LikeIgnoreCase.class)})
            }) Specification<User> spec
    ) {
        return userService.userCount(spec);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getUsers() {
        return userService.getUsers();
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
