package es.odec.pruebas.services;

import es.odec.pruebas.models.Permission;
import es.odec.pruebas.repositories.PermissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    PermissionRepo permissionRepo;

    // Recoger datos de los permisos
    @Override
    public ResponseEntity<List<Permission>> getPermissions() {
        return ResponseEntity.ok().body(permissionRepo.findAll());
    }

    public ResponseEntity<?> getPermissionsPaged(Pageable pageable, Specification<Permission> spec) {
        try {
            Page<Permission> result = permissionRepo.findAll(spec, pageable);

            if (!result.hasContent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ha ocurrido un error: " + e.getMessage());
        }
    }

    public ResponseEntity<?> permissionCount(Specification<Permission> spec) {
        long result = permissionRepo.count(spec);
        return ResponseEntity.ok().body(result);
    }

    @Override
    public ResponseEntity<Permission> getPermission(int id) {
        if (permissionRepo.existsById(id)) {
            return ResponseEntity.ok().body(permissionRepo.findById(id).get());
        } else
            return ResponseEntity.notFound().build();
    }

    // Crear un permiso
    @Override
    public ResponseEntity<Permission> createPermission(Permission permission) {
        if (permission.getType() == null) {
            return ResponseEntity.badRequest().body(permission);
        }
        return ResponseEntity.ok().body(permissionRepo.save(permission));
    }

    // Modificar permisos
    @Override
    public ResponseEntity<Permission> updatePermission(Permission permission, int id) {
        Permission editable = permissionRepo.findById(id).get();
        if (editable != null) {
            editable.setType(permission.getType());
            return ResponseEntity.ok().body(permissionRepo.save(editable));
        } else
            return ResponseEntity.notFound().build();
    }

    // Eliminar permiso
    @Override
    public ResponseEntity<Permission> deletePermission(int id) {
        if (permissionRepo.findById(id).isPresent()) {
            permissionRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }
}
