package app.tracksmart.trackSmart.dto;

import lombok.Data;

@Data
public class UserRegisterationDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String role;
}
