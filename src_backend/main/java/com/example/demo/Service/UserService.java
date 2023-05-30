package com.example.demo.Service;

import com.example.demo.controllers.DTO.FlightDTO;
import com.example.demo.controllers.DTO.UserDTO;
import com.example.demo.entity.Flight;
import com.example.demo.entity.User;
import com.example.demo.entity.User_type;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    UserDTO findByEmail(String name);
    User findById(Long id);
    User insertUser(User user);
    UserDTO loginUser(String username, String password);
    UserDTO logOut(String username);
    UserDTO loginAdmin(String username, String password);
    List<UserDTO> findAllUsers();
    String forgotPassword(String email);
    User updateUser(String email, String old_password, String password);
    List<UserDTO>  findBylogInOrNot();
    String findName(String email);
    List<FlightDTO> addFavorites(String number, String email);
    List<FlightDTO> findFavorites(String email);
    List<FlightDTO> removeFavorite(String email, String number);
}
