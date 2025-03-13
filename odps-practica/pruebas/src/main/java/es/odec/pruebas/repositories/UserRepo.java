package es.odec.pruebas.repositories;

import es.odec.pruebas.models.User;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User> {
    User findByUsername(String username);
}
