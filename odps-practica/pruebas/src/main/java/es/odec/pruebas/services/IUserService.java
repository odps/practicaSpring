package es.odec.pruebas.services;

import es.odec.pruebas.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    ResponseEntity<List<User>> getUsers();

    ResponseEntity<User> getUser(int id);

    ResponseEntity<User> createUser(User user);

    ResponseEntity<User> updateUser(User user, int id);

    ResponseEntity<User> deleteUser(int id);

}
