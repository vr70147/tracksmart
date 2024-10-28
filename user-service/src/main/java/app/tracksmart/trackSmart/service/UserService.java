package app.tracksmart.trackSmart.service;

import app.tracksmart.trackSmart.dto.UserRegisterationDTO;
import app.tracksmart.trackSmart.model.User;
import app.tracksmart.trackSmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegisterationDTO registerationDTO) {
        // Check if user already exist
        if (userRepository.existsByUsername(registerationDTO.getUsername())) {
            throw new IllegalStateException("User already taken");
        }

        // Encrypt password
        String encodedPassword = passwordEncoder.encode(registerationDTO.getPassword());

        // Create new user
        User user = new User();
        user.setUsername(registerationDTO.getUsername());
        user.setPassword(registerationDTO.getPassword());
        user.setEmail(registerationDTO.getEmail());
        user.setPhone(registerationDTO.getPhone());
        user.setRole(registerationDTO.getRole());

        // Save the new user
        return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException("User not found"));
    }


}



