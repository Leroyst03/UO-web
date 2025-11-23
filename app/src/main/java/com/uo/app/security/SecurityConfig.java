package com.uo.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // públicas
                .requestMatchers("/", "/login", "/publicacion/**").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/favicon.ico").permitAll()

                // vistas / recursos protegidos por rol
                .requestMatchers("/usuarios/nuevo").hasRole("PROFESOR")

                // auth endpoints
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/logout").authenticated()      // logout requiere estar autenticado
                .requestMatchers("/auth/sign-up").hasRole("PROFESOR") // alta solo PROFESOR

                // API publicaciones
                .requestMatchers(HttpMethod.GET, "/api/publicaciones/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/publicaciones/**").hasAnyRole("PROFESOR","ALUMNO")
                .requestMatchers(HttpMethod.PUT, "/api/publicaciones/**").hasAnyRole("PROFESOR","ALUMNO")
                .requestMatchers(HttpMethod.DELETE, "/api/publicaciones/**").hasRole("PROFESOR")

                // resto requiere autenticación
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
