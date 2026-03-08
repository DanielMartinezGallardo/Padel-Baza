package com.padel.back.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

import com.padel.back.entity.Sexo;
import com.padel.back.entity.Usuario;
import com.padel.back.repository.RoleRepository;
import com.padel.back.repository.UsuarioRepository;
import com.padel.back.entity.Role;

import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    //     http
    //         .csrf(csrf -> csrf.disable())
    //         .authorizeHttpRequests(auth -> auth
    //             .requestMatchers("/auth/**").permitAll()
    //             .anyRequest().authenticated()
    //         )
    //         .httpBasic(Customizer.withDefaults()); 

    //     return http.build();
    // }
@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> {}) // 🔥 activa CORS
            .csrf(csrf -> csrf.disable()) // para pruebas
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/login").permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }








    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner init(UsuarioRepository usuarioRepository,
                        RoleRepository roleRepository,
                        PasswordEncoder passwordEncoder) {
        return args -> {

            if (usuarioRepository.findByEmail("admin@padel.com").isEmpty()) {

                Role role = roleRepository.findByNombre("ADMIN")
                        .orElseThrow();

                        
                Usuario user = new Usuario();
                user.setNombre("Admin");
                user.setEmail("admin@padel.com");
                user.setPassword(passwordEncoder.encode("1234"));
                user.setSexo(Sexo.masculino);
                user.setActivo(true);
                user.setRole(role);

                usuarioRepository.save(user);
            }
        };
    }


    

}
