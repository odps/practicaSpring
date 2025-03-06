package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Role;
import es.odec.pruebas.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    //Obtener informacion de los roles
    @GetMapping("/list")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable int id) {
        return ResponseEntity.ok().body(roleService.getRole(id));
    }

    //Crear Roles
    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    //Editar roles
    @PutMapping("/edit/{id}")
    public ResponseEntity<Role> editRole(@PathVariable int id, @RequestBody Role role) {
        return roleService.update(role, id);
    }

    //Eliminar Roles
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRole(@PathVariable int id) {
        return roleService.deleteRole(id);
    }
}
