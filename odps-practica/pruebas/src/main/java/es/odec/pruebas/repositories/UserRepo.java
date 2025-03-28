package es.odec.pruebas.repositories;

import es.odec.pruebas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, Integer> {

    User findByUsername(String username);

    // @Query(value = "CALL op_paquete2.obtenerusuario(:id)", nativeQuery = true)
    // @Query(value = "SELECT op_paquete2.obtenerusuario(:id) from DUAL",
    // nativeQuery = true)
    // User findUserByIdQuery(@Param("id") int id);

    // @Query(value = "select op_paquete.objetousuario(:id) from dual", nativeQuery
    // = true)
    // User findUserByIdQuery(@Param("id") int id);

    @Query(value = "SELECT * FROM TABLE(op_paquete2.objetousuario(:id))", nativeQuery = true)
    User findUserByIdQuery(@Param("id") int id);

}
