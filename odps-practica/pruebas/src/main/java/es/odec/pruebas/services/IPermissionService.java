package es.odec.pruebas.services;

import es.odec.pruebas.models.Permission;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPermissionService {
    //Recoger datos de los permisos
    ResponseEntity<List<Permission>> getPermissions();

    ResponseEntity<Permission> getPermission(int id);

    //Crear un permiso
    ResponseEntity<Permission> createPermission(Permission permission);

    //Modificar permisos
    ResponseEntity<Permission> updatePermission(Permission permission, int id);

    //Eliminar permiso
    ResponseEntity<Permission> deletePermission(int id);
}
