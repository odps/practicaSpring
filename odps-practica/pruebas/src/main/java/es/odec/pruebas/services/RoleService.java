package es.odec.pruebas.services;

import es.odec.pruebas.models.Role;
import es.odec.pruebas.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    //Recoger datos de roles
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(roleRepo.findAll());
    }

    public ResponseEntity<Role> getRole(int roleId) {
        if (roleRepo.existsById(roleId)) {
            return ResponseEntity.ok().body(roleRepo.findById(roleId).get());
        } else return ResponseEntity.status(404).build();
    }

    //Crear un rol
    public ResponseEntity<Role> createRole(Role role) {
        return ResponseEntity.ok().body(roleRepo.save(role));
    }

    // Modificar roles
    public ResponseEntity<Role> update(Role role, int id) {

        Role editable = roleRepo.findById(id).get();

        if (editable != null) {

            String roleName = role.getRoleName();

            if (roleName != null) {
                editable.setRoleName(roleName);
            }

            roleRepo.save(editable);


        }
        return ResponseEntity.ok().body(editable);
    }

    //Eliminar Roles
    public ResponseEntity deleteRole(int id) {
        if (roleRepo.existsById(id)) {
            roleRepo.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
