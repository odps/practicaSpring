package es.odec.pruebas.config;

import es.odec.pruebas.services.CustomUserDetailsService;
import es.odec.pruebas.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable().cors().disable()
                .authorizeRequests().anyRequest().permitAll()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().logout().permitAll();

        //Security config oficial, deshabilitado para pruebas

//        http.csrf().disable().cors().disable()
//                .authorizeRequests()
//                // Publicos
//                .requestMatchers("/register", "/login").permitAll()
//
//                // Admin .hasAuthority("MANAGE_USERS")
//                .requestMatchers("/user/**", "/role/**", "/permission/**").hasAnyAuthority("CREATE", "DELETE", "UPDATE")
//
//                // Shop
//                .requestMatchers("/shop/**").hasAnyAuthority("CREATE")
//
//                // Product
//                .requestMatchers("/product/create", "/product/edit/**", "/product/delete/**")
//                .hasAnyAuthority("CREATE")
//                .requestMatchers("/product/list").hasAuthority("CREATE")
//
//                // Stock
//                .requestMatchers("/stock/**").hasAuthority("CREATE")
//
//                // Order
//                .requestMatchers("/order/create").hasAuthority("CREATE")
//                .requestMatchers("/order/**").hasAnyAuthority("CREATE")
//
//                // Invoice
//                .requestMatchers("/invoice/**").authenticated()
//
//                //Hello para pruebas
//                .requestMatchers("/hello").authenticated()
//
//                .anyRequest().authenticated()
//                .and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().logout().permitAll();

        http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(jwtUtil, customUserDetailsService);
    }
}
