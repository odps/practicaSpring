package es.odec.pruebas.controllers;

import es.odec.pruebas.models.User;
import es.odec.pruebas.services.UserService;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

//    // Coger datos de los usuarios
//    @GetMapping("/pagedList")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    public ResponseEntity<?> getPagedUsers(
//            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
//            @Conjunction({
//                    @Or({@Spec(path = "firstName", params = "hasName", spec = EqualIgnoreCase.class),
//                            @Spec(path = "firstName", params = "likeName", spec = LikeIgnoreCase.class)}),
//                    @Or({@Spec(path = "lastName", params = "hasLastName", spec = EqualIgnoreCase.class),
//                            @Spec(path = "lastName", params = "likeLastName", spec = LikeIgnoreCase.class)}),
//                    @Or({@Spec(path = "email", params = "hasEmail", spec = EqualIgnoreCase.class),
//                            @Spec(path = "email", params = "likeEmail", spec = LikeIgnoreCase.class)})
//            }) Specification<User> spec) {
//        return userService.getUsers(pageable, spec);
//    }

    // Coger datos de los usuarios
    @GetMapping("/pagedList")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getPagedUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "userId") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @Conjunction({
                    @Or({@Spec(path = "firstName", params = "hasName", spec = EqualIgnoreCase.class),
                            @Spec(path = "firstName", params = "likeName", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "lastName", params = "hasLastName", spec = EqualIgnoreCase.class),
                            @Spec(path = "lastName", params = "likeLastName", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "email", params = "hasEmail", spec = EqualIgnoreCase.class),
                            @Spec(path = "email", params = "likeEmail", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "createdAt", params = "createdAfter", spec = GreaterThanOrEqual.class),
                            @Spec(path = "createdAt", params = "createdBefore", spec = LessThanOrEqual.class)}),
            }) Specification<User> spec) {
        return userService.getUsers(page, size, new String[]{sort}, direction, spec);
    }

    @GetMapping("/userCount")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> userCount(
            @Conjunction({
                    @Or({@Spec(path = "firstName", params = "hasName", spec = EqualIgnoreCase.class),
                            @Spec(path = "firstName", params = "likeName", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "lastName", params = "hasLastName", spec = EqualIgnoreCase.class),
                            @Spec(path = "lastName", params = "likeLastName", spec = LikeIgnoreCase.class)})
            }) Specification<User> spec) {
        return userService.userCount(spec);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    // Crear un usuario
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Editar un usuario
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id) {
        return userService.updateUser(user, id);
    }

    // Eliminar un usuario
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
