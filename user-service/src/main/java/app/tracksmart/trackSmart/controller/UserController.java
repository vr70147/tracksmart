package app.tracksmart.trackSmart.controller;

import app.tracksmart.trackSmart.dto.UserRegisterationDTO;
import app.tracksmart.trackSmart.model.User;
import app.tracksmart.trackSmart.repository.UserRepository;
import app.tracksmart.trackSmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegisterationDTO registerationDTO) {
        User newUser = userService.registerUser(registerationDTO);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
