package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Product;
import es.odec.pruebas.services.ProductService;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
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


@RestController
@EnableMethodSecurity(prePostEnabled = true)
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Rutas para obtener productos
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> productList() {
        System.out.println("Entrada a controlador lista");
        return productService.getProducts();
    }

    @GetMapping("/pagedList")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getPagedProducts(
            @PageableDefault(page = 0, size = 10, sort = "productId", direction = Sort.Direction.ASC) Pageable pageable,
            @Conjunction({
                    @Or({@Spec(path = "productName", params = "hasName", spec = EqualIgnoreCase.class),
                            @Spec(path = "productName", params = "likeName", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "productPrice", params = "priceGreater", spec = GreaterThanOrEqual.class),
                            @Spec(path = "productPrice", params = "priceLess", spec = LessThanOrEqual.class)}),
                    @Or({@Spec(path = "productDescription", params = "hasDescription", spec = EqualIgnoreCase.class),
                            @Spec(path = "productDescription", params = "likeDescription", spec = LikeIgnoreCase.class)})
            }) Specification<Product> spec) {
        return productService.getProductsPaged(pageable, spec);
    }

    @GetMapping("/productCount")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> productCount(
            @Conjunction({
                    @Or({@Spec(path = "productName", params = "hasName", spec = EqualIgnoreCase.class),
                            @Spec(path = "productName", params = "likeName", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "productPrice", params = "priceGreater", spec = GreaterThanOrEqual.class),
                            @Spec(path = "productPrice", params = "priceLess", spec = LessThanOrEqual.class)})
            }) Specification<Product> spec) {
        return productService.productCount(spec);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Product> productById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    // Ruta para la creacion de productos
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void createProduct(@RequestBody Product product) {
        productService.save(product);
    }

    // Ruta para la modificacion de productos
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int id) {
        return productService.update(product, id);
    }

    // Ruta para la eliminacion de productos
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteProduct(@PathVariable int id) {
        productService.delete(id);
    }
}
