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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // Recuperar informacion sobre las listas
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<Permission>> getPermissions() {
        return permissionService.getPermissions();
    }

    @GetMapping("/pagedList")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getPagedPermissions(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @Conjunction({
                    @Or({@Spec(path = "type", params = "hasType", spec = EqualIgnoreCase.class),
                            @Spec(path = "type", params = "likeType", spec = LikeIgnoreCase.class)})
            }) Specification<Permission> spec) {
        return permissionService.getPermissionsPaged(pageable, spec);
    }

    @GetMapping("/permissionCount")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> permissionCount(
            @Conjunction({
                    @Or({@Spec(path = "type", params = "hasType", spec = EqualIgnoreCase.class),
                            @Spec(path = "type", params = "likeType", spec = LikeIgnoreCase.class)})
            }) Specification<Permission> spec) {
        return permissionService.permissionCount(spec);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Permission> getPermission(@PathVariable int id) {
        return permissionService.getPermission(id);
    }

    // Crear un permiso
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        return permissionService.createPermission(permission);
    }

    // Modificar un permiso
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Permission> editPermission(@RequestBody Permission permission, @PathVariable int id) {
        return permissionService.updatePermission(permission, id);
    }

    // Eliminar un permiso
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Permission> deletePermission(@PathVariable int id) {
        return permissionService.deletePermission(id);
    }

}
