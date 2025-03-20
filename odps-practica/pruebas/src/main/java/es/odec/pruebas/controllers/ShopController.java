package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Shop;
import es.odec.pruebas.services.ShopService;
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
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    // Coger datos de las tiendas
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<Shop>> getShops() {
        return shopService.getShops();
    }

    @GetMapping("/pagedList")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getPagedShops(
            @PageableDefault(page = 0, size = 10, sort = "shopId", direction = Sort.Direction.ASC) Pageable pageable,
            @Conjunction({
                    @Or({@Spec(path = "shopName", params = "hasName", spec = EqualIgnoreCase.class),
                            @Spec(path = "shopName", params = "likeName", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "shopEmail", params = "hasEmail", spec = EqualIgnoreCase.class),
                            @Spec(path = "shopEmail", params = "likeEmail", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "shopAdress", params = "hasAddress", spec = EqualIgnoreCase.class),
                            @Spec(path = "shopAdress", params = "likeAddress", spec = LikeIgnoreCase.class)})
            }) Specification<Shop> spec) {
        return shopService.getShopsPaged(pageable, spec);
    }

    @GetMapping("/shopCount")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> shopCount(
            @Conjunction({
                    @Or({@Spec(path = "shopName", params = "hasName", spec = EqualIgnoreCase.class),
                            @Spec(path = "shopName", params = "likeName", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "shopEmail", params = "hasEmail", spec = EqualIgnoreCase.class),
                            @Spec(path = "shopEmail", params = "likeEmail", spec = LikeIgnoreCase.class)})
            }) Specification<Shop> spec) {
        return shopService.shopCount(spec);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Shop> getShop(@PathVariable int id) {
        return shopService.getShop(id);
    }

    // Crear una tienda
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        return shopService.createShop(shop);
    }

    // Editar una tienda
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Shop> updateShop(@RequestBody Shop shop, @PathVariable int id) {
        return shopService.updateShop(shop, id);
    }

    // Eliminar una tienda
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Shop> deleteShop(@PathVariable int id) {
        return shopService.deleteShop(id);
    }
}
