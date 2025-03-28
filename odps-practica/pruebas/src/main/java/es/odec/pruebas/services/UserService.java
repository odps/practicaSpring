package es.odec.pruebas.services;

import es.odec.pruebas.models.User;
import es.odec.pruebas.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Recoger usuarios
    @Override
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok().body(users);
    }

    public ResponseEntity<User> getUserByUsername(String username){
        User user = userRepo.findByUsername(username);
        return ResponseEntity.ok().body(user);
    }

    //Recoger usuarios con paginado manual
//    public ResponseEntity<List<User>> getUsers(int page, int size) {
//        List<User> users = userRepo.findAll();
//        List<User> paginated = users.subList((page - 1) * size, page * size);
//
//        return ResponseEntity.ok().body(paginated);
//    }

    //Recoger usuarios con JPA Pageable
//    public ResponseEntity<?> getUsers(int page, int size, String sort, String filter) {
//        try {
//            Sort.Direction sortMethod = Sort.Direction.fromString(sort);
//            Pageable pageable = PageRequest.of(page, size, Sort.by(sortMethod, filter));
//            Page<User> result = userRepo.findAll(pageable);
//            System.out.println(sort + " " + filter);
//            return ResponseEntity.ok().body(result);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ha ocurrido un error: " + e.getMessage());
//        }
//    }

//    public ResponseEntity<?> getUsers(int page, int size, String[] sortParams, String sort, Specification<User> spec) {
//        try {
//            //Determina direccion del sort (ASC,DESC,REVERSE)
//            Sort.Direction sortMethod = Sort.Direction.fromString(sort);
//
//            //Ordena los resultados segun el Array de parametros enviados
//            Pageable pageable = PageRequest.of(page, size, Sort.by(sortMethod, sortParams));
//            Page<User> result = userRepo.findAll(spec, pageable);
//
//            if(!result.hasContent()){
//                return ResponseEntity.notFound().build();
//            }
//
//            return ResponseEntity.ok().body(result);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Ha ocurrido un error: " + e.getMessage());
//        }
//    }

    public ResponseEntity<?> getUsers(Pageable pageable, Specification<User> spec) {
        try {
            Page<User> result = userRepo.findAll(spec, pageable);

            if(!result.hasContent()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ha ocurrido un error: " + e.getMessage());
        }
    }


    @Override
    public ResponseEntity<User> getUser(int id) {
        if (!userRepo.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userRepo.findById(id).get());
    }

    public ResponseEntity<?> userCount(Specification<User> spec) {
        long result = userRepo.count(spec);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        if (user == null || userRepo.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().build();
        }
        // Codifica la contraseña del usuario
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok().body(userRepo.save(user));
    }

    @Override
    public ResponseEntity<User> updateUser(User oldUser, int id) {

        if (!userRepo.existsById(id)) {
            System.out.println("Usuario no encontrado");
            return ResponseEntity.status(404).build();
        }

        User userNew = userRepo.findById(id).get();
        userNew.setFirstName(oldUser.getFirstName());
        userNew.setLastName(oldUser.getLastName());
        userNew.setEmail(oldUser.getEmail());
        userNew.setUsername(oldUser.getUsername());
        userNew.setRole(oldUser.getRole());
        userNew.setShops(oldUser.getShops());
        userNew.setInvoices(oldUser.getInvoices());
        User saved = userRepo.save(userNew);

        return ResponseEntity.ok().body(saved);
    }

    @Override
    public ResponseEntity<User> deleteUser(int id) {
        if (!userRepo.existsById(id)) {
            System.out.println("Usuario no encontrado");
            return ResponseEntity.status(404).build();
        }
        System.out.println(userRepo.existsById(id));
        userRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
