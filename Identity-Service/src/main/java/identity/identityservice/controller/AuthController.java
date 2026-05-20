package identity.identityservice.controller;

import identity.identityservice.dto.RegisterRequest;
import identity.identityservice.dto.UserResponse;
import identity.identityservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/test-token")
    public ResponseEntity<TokenResponse> testToken(@RequestParam String username) {
        TokenResponse response = authService.generateTokenForUser(username, jwtUtil);
        return ResponseEntity.ok(response);
    }

}
