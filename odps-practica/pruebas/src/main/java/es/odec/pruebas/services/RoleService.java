package es.odec.pruebas.services;

import es.odec.pruebas.models.Permission;
import es.odec.pruebas.models.Role;
import es.odec.pruebas.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepo roleRepo;

    //Recoger datos de roles
    @Override
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(roleRepo.findAll());
    }

    @Override
    public ResponseEntity<Role> getRole(int roleId) {
        if (roleRepo.existsById(roleId)) {
            return ResponseEntity.ok().body(roleRepo.findById(roleId).get());
        } else return ResponseEntity.status(404).build();
    }

    //Crear un rol
    @Override
    public ResponseEntity<Role> createRole(Role role) {
        return ResponseEntity.ok().body(roleRepo.save(role));
    }

    // Modificar roles
    @Override
    public ResponseEntity<Role> editRole(Role roleOld, int id) {
        if (!roleRepo.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        Role roleNew = roleRepo.findById(id).get();
        roleNew.setPermissions(roleOld.getPermissions());
        roleNew.setRoleName(roleOld.getRoleName());
        Role saved = roleRepo.save(roleNew);
        return ResponseEntity.ok().body(saved);
    }

    //Eliminar Roles
    @Override
    public ResponseEntity<Void> deleteRole(int id) {
        if (roleRepo.existsById(id)) {
            roleRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    //Recuperar permisos de un role
    @Override
    public ResponseEntity<Set<Permission>> getPermissions(int id) {
        if (roleRepo.existsById(id)) {
            Role role = roleRepo.findById(id).get();
            return ResponseEntity.ok().body(role.getPermissions());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    //Agregar permisos
    @Override
    public ResponseEntity<Set<Permission>> setPermissions(int id, Set<Permission> permissions) {
        if (roleRepo.existsById(id)) {
            Role role = roleRepo.findById(id).get();
            role.setPermissions(permissions);
            roleRepo.save(role);
            return ResponseEntity.ok().body(role.getPermissions());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

}

//CODIGO DE VICTOR, AGREGAR PERMISOS A LOS ROLES

//public ResponseEntity<Role> update(Role roleOld, int id) {
//
//    if (!roleRepo.existsById(id)) {
//        return ResponseEntity.badRequest().body(roleRepo.findById(id).get());
//    }
//
//    Role roleNew = roleRepo.findById(id).get();
//    roleNew.setPermissions(roleOld.getPermissions());
//    roleNew.setRoleName(roleOld.getRoleName());
//    Role saved = roleRepo.save(roleNew);
//    return ResponseEntity.ok().body(saved);
//
//}
