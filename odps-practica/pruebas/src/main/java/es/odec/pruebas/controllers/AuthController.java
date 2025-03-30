package es.odec.pruebas.controllers;

import es.odec.pruebas.models.AuthUser;
import es.odec.pruebas.models.User;
import es.odec.pruebas.services.CustomUserDetailsService;
import es.odec.pruebas.services.UserService;
import es.odec.pruebas.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
@RequestMapping("/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        try {

            // Crea el usuario en la BB.DD
            userService.createUser(user);
            return "User registered successfully";
        } catch (Exception e) {
            return "Ha ocurrido un error al registrar el usuario" + e.getMessage();
        }

    }

//    @CrossOrigin(origins = "*")
@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody User authUser) {
    try {
        System.out.println("=== Attempting authentication for user: " + authUser.getUsername());

        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword())
        );

        System.out.println("=== Authentication successful");

        // Load user details and generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authUser.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        // Get full user information
        final User user = userService.getUserByUsername(authUser.getUsername()).getBody();

        return ResponseEntity.ok(new AuthUser(jwt, user));
    } catch (Exception e) {
        System.out.println("=== Authentication failed: " + e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
    }
}

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}

