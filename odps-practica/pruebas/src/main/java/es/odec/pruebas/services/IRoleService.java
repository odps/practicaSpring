package es.odec.pruebas.services;

import es.odec.pruebas.models.Permission;
import es.odec.pruebas.models.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface IRoleService {
    public ResponseEntity<List<Role>> getRoles();

    public ResponseEntity<Role> getRole(int roleId);

    public ResponseEntity<Role> createRole(Role role);

    public ResponseEntity<Role> editRole(Role roleOld, int id);

    public ResponseEntity deleteRole(int id);

    //Gestion de permisos
    public ResponseEntity<Set<Permission>> getPermissions(int id);

    public ResponseEntity<Set<Permission>> setPermissions(int id, Set<Permission> permissions);
}
