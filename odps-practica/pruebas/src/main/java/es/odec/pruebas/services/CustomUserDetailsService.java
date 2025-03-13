package es.odec.pruebas.services;

import es.odec.pruebas.models.Permission;
import es.odec.pruebas.models.User;
import es.odec.pruebas.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    //    Metodos para recuperar las autoridades de un usuario
    public Collection<GrantedAuthority> mapToAuthorities(Set<Permission> permissions) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Permission permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getType()));
        }

        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=== Entrada en controlador de login");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("=== Permisos encontrados de user en login: " + mapToAuthorities(user.getRole().getPermissions()));
        if (user.getRole().getPermissions().isEmpty()) {
            System.out.println("Este usuario no tiene rol asignados");
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapToAuthorities(user.getRole().getPermissions()));
        //        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
