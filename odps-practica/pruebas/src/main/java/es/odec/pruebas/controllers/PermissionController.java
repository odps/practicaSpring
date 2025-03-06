package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Permission;
import es.odec.pruebas.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //Recuperar informacion sobre las listas
    @GetMapping("/list")
    public ResponseEntity<List<Permission>> getPermissions() {
        return permissionService.getPermissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermission(@PathVariable int id) {
        return permissionService.getPermission(id);
    }

    //Crear un permiso
    @PostMapping("/create")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        return permissionService.createPermission(permission);
    }

    //Modificar un permiso
    @PutMapping("/edit/{id}")
    public ResponseEntity<Permission> editPermission(@RequestBody Permission permission, @PathVariable int id) {
        return permissionService.updatePermission(permission, id);
    }

    //Eliminar un permiso
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Permission> deletePermission(@PathVariable int id) {
        return permissionService.deletePermission(id);
    }

}
