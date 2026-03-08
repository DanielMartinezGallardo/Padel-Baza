package com.padel.back.controller;

import com.padel.back.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {

    private final PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;

    LoginController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {

    String email = body.get("email");
    String password = body.get("password");

    if(email == null || email.isBlank() || !email.matches(".+@.+\\..+")) 
    {
        return ResponseEntity.badRequest().body("Email no válido");
    }

    if(password == null || password.isBlank()) 
    {
        return ResponseEntity.badRequest().body("Contraseña no puede estar vacía");
    }

    if(usuarioRepository.existsByEmail(email) && passwordEncoder.matches(password, usuarioRepository.findByEmail(email).get().getPassword()))
    {
        return ResponseEntity.ok("Login exitoso para: " + email);
    }
    

    return ResponseEntity.status(401).body("Credenciales inválidas");
    
}
}