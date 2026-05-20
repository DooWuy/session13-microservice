package identity.identityservice.service;

import identity.identityservice.dto.RegisterRequest;
import identity.identityservice.dto.TokenResponse;
import identity.identityservice.dto.UserResponse;
import identity.identityservice.entity.User;
import identity.identityservice.repository.UserRepository;
import identity.identityservice.security.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .build();
    }

    public TokenResponse generateTokenForUser(String username, JwtUtil jwtUtil) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));

        String token = jwtUtil.generateToken(user);

        return TokenResponse.builder()
                .token(token)
                .username(user.getUsername())
                .build();
    }
}
