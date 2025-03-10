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

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    //Metodos para recuperar las autoridades de un usuario
    public Collection<GrantedAuthority> mapToAuthorities(Set<Permission> permissions) {
        return permissions.stream().map(permission -> new SimpleGrantedAuthority(permission.getType())).collect(Collectors.toSet());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapToAuthorities(user.getRole().getPermissions()));
    }
}
