package ru.naumov.bloghub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.naumov.bloghub.model.RegisterDTO;
import ru.naumov.bloghub.model.User;
import ru.naumov.bloghub.repository.UserRepository;
import ru.naumov.bloghub.security.JWTUtil;
import ru.naumov.bloghub.service.RegistrationService;

import java.time.Instant;
import java.util.Map;

@Controller
@RestController("/api/")
@RequiredArgsConstructor
public class HomeController {
    private final JWTUtil jwtUtil;
    private final RegistrationService registrationService;

    @PostMapping("/reg")
    public ResponseEntity<Map<String, String>> home(@RequestBody @Valid RegisterDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Ошибка"));
        }

        User user = convertToUser(dto);
        registrationService.register(user);
        String jwtToken = jwtUtil.generateTokenBySubject(user.getUsername());
        return ResponseEntity.ok(Map.of("token", jwtToken));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Success");
    }

    private User convertToUser(RegisterDTO dto) {
        User user = new User();
        user.setEmail("reptiloydforver@gmail.com");
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(Instant.now());

        return user;
    }
}
