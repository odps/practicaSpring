package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Permission;
import es.odec.pruebas.services.PermissionService;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // Recuperar informacion sobre las listas
    @GetMapping("/list")
    public ResponseEntity<List<Permission>> getPermissions() {
        return permissionService.getPermissions();
    }

    @GetMapping("/pagedList")
    public ResponseEntity<?> getPagedPermissions(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @Conjunction({
                    @Or({@Spec(path = "type", params = "hasType", spec = EqualIgnoreCase.class),
                            @Spec(path = "type", params = "likeType", spec = LikeIgnoreCase.class)})
            }) Specification<Permission> spec) {
        return permissionService.getPermissionsPaged(pageable, spec);
    }

    @GetMapping("/permissionCount")
    public ResponseEntity<?> permissionCount(
            @Conjunction({
                    @Or({@Spec(path = "type", params = "hasType", spec = EqualIgnoreCase.class),
                            @Spec(path = "type", params = "likeType", spec = LikeIgnoreCase.class)})
            }) Specification<Permission> spec) {
        return permissionService.permissionCount(spec);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermission(@PathVariable int id) {
        return permissionService.getPermission(id);
    }

    // Crear un permiso
    @PostMapping("/create")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        return permissionService.createPermission(permission);
    }

    // Modificar un permiso
    @PutMapping("/edit/{id}")
    public ResponseEntity<Permission> editPermission(@RequestBody Permission permission, @PathVariable int id) {
        return permissionService.updatePermission(permission, id);
    }

    // Eliminar un permiso
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Permission> deletePermission(@PathVariable int id) {
        return permissionService.deletePermission(id);
    }

}
