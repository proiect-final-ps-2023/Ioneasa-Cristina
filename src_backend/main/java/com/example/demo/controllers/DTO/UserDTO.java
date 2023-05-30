package com.example.demo.controllers.DTO;

import com.example.demo.entity.User_type;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private User_type type;
    private boolean logged;
}
