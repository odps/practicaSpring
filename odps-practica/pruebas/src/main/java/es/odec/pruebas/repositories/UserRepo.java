package es.odec.pruebas.repositories;

import es.odec.pruebas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    //@Query(value = "CALL op_paquete2.obtenerusuario(:id)", nativeQuery = true)
    @Query(value = "SELECT op_paquete2.obtenerusuario(:id) from DUAL", nativeQuery = true)
    User findUserByIdQuery(@Param("id") int id);


    // Call a function from your package that returns a single value
//    @Query(value = "SELECT YOUR_PACKAGE_NAME.YOUR_FUNCTION_NAME(:productId) FROM dual", nativeQuery = true)
//    String callCustomFunction(@Param("productId") int productId);
//
//    // Call a function that returns a cursor which you want to map to entities
//    @Query(value = "SELECT * FROM TABLE(YOUR_PACKAGE_NAME.GET_PRODUCTS_FUNCTION(:category))", nativeQuery = true)
//    List<Product> getProductsByCategory(@Param("category") String category);
//
//    // To execute a procedure, you can use the @Modifying annotation
//    @Modifying
//    @Query(value = "BEGIN YOUR_PACKAGE_NAME.YOUR_PROCEDURE_NAME(:param1, :param2); END;", nativeQuery = true)
//    void executePackageProcedure(@Param("param1") int param1, @Param("param2") String param2);


}
