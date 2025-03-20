package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Permission;
import es.odec.pruebas.models.Role;
import es.odec.pruebas.services.RoleService;
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
import java.util.Set;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Obtener informacion de los roles
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<Role>> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/pagedList")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getPagedRoles(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @Conjunction({
                    @Or({@Spec(path = "roleName", params = "hasName", spec = EqualIgnoreCase.class),
                            @Spec(path = "roleName", params = "likeName", spec = LikeIgnoreCase.class)})
            }) Specification<Role> spec) {
        return roleService.getRolesPaged(pageable, spec);
    }

    @GetMapping("/roleCount")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> roleCount(
            @Conjunction({
                    @Or({@Spec(path = "roleName", params = "hasName", spec = EqualIgnoreCase.class),
                            @Spec(path = "roleName", params = "likeName", spec = LikeIgnoreCase.class)})
            }) Specification<Role> spec) {
        return roleService.roleCount(spec);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Role> getRole(@PathVariable int id) {
        return roleService.getRole(id);
    }

    // Crear Roles
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    // Editar roles
    @PutMapping("/edit/{roleId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Role> editRole(@PathVariable int roleId, @RequestBody Role role) {
        return roleService.editRole(role, roleId);
    }

    // Eliminar Roles
    @DeleteMapping("/delete/{roleId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteRole(@PathVariable int roleId) {
        return roleService.deleteRole(roleId);
    }

    // Ver Permisos
    @GetMapping("/permission/{roleId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Set<Permission>> getPermissions(@PathVariable int roleId) {
        return roleService.getPermissions(roleId);
    }

    // Agregar Permisos
    @PutMapping("/permission/{roleId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Set<Permission>> setPermissions(@PathVariable int roleId,
                                                          @RequestBody Set<Permission> permissions) {
        return roleService.setPermissions(roleId, permissions);
    }
}
