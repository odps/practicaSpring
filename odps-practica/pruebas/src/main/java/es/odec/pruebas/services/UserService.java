package es.odec.pruebas.services;

import es.odec.pruebas.models.User;
import es.odec.pruebas.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userRepo.findAll());
    }

    @Override
    public ResponseEntity<User> getUser(int id) {
        if (!userRepo.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userRepo.findById(id).get());
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
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
        userNew.setPassword(oldUser.getPassword());
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
