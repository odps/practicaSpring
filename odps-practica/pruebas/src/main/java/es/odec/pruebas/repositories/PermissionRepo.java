package es.odec.pruebas.repositories;

import es.odec.pruebas.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {
}
