package com.example.demo.controllers;

import com.example.demo.Service.FlightService;
import com.example.demo.Service.UserService;
import com.example.demo.Service.emailServiceImplementare;
import com.example.demo.controllers.DTO.FlightDTO;
import com.example.demo.controllers.DTO.UserDTO;
import com.example.demo.controllers.mapper.FlightMapper;
import com.example.demo.entity.Flight;
import com.example.demo.entity.User;
import com.example.demo.entity.User_type;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FlightService flightService;

    @Autowired
    private emailServiceImplementare emailSenderService;

    @CrossOrigin(origins = "http://localhost:4200/")
   @PutMapping("/updatePassword")
    public User updatePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword){
       System.out.println("parola veche " + oldPassword);
       System.out.println("parola noua " + newPassword);
       return userService.updateUser(email, oldPassword, newPassword);
   }

    @GetMapping("/findall")
    public List<UserDTO> findAllUsers() { return userService.findAllUsers();}

   @PostMapping("/insertUser")
    public User insertUser(@RequestBody User user){
       user.setType(User_type.CLIENT);
       return userService.insertUser(user);
   }

    @PutMapping("/insertUser")
    public User insertUser(@RequestBody User user, @RequestParam String number){
        Flight flight = FlightMapper.mapDTOToModel(flightService.findByNumber(number));
        //user.setFlightToUser(flight);
        return userService.insertUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody User user) {
        System.out.println("in controler:" + user.getPassword());
        UserDTO loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
        if (loggedInUser == null) {
            return ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.ok(loggedInUser);
        }
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<UserDTO> loginAdmin(@RequestBody User user) {
        if( userService.loginAdmin(user.getEmail(), user.getPassword()) == null)
            return (ResponseEntity<UserDTO>) ResponseEntity.internalServerError();
        else return ResponseEntity.ok(userService.loginAdmin(user.getEmail(), user.getPassword()));
    }

    @PostMapping("/logout")
    private ResponseEntity<UserDTO>logOut(@RequestParam String email) {
        return ResponseEntity.ok(userService.logOut(email));
    }

    @PostMapping("/send_password")
    public ResponseEntity<String> sendPassword(@RequestParam String email) throws MessagingException {
        emailSenderService.sendMailWithAttachment(email,
                "Your password is:" + userService.forgotPassword(email),
                "Password requested");
       return ResponseEntity.ok(userService.forgotPassword(email));
    }

    @GetMapping("/findByEmail")
    public UserDTO findByEmail(@RequestParam String email){
       return userService.findByEmail(email);
    }

    @GetMapping("/findByLogIn")
    public List<UserDTO> findByLogInOrNot(){
        return userService.findBylogInOrNot();
    }

    @GetMapping("/findName")
    public String findName(@RequestParam String email){
       return userService.findName(email);
    }

    @PostMapping("/addFavorite")
    public List<FlightDTO> addFavorites(@RequestParam String email ,@RequestParam String number){
        return userService.addFavorites(email, number);
    }

    @GetMapping("/findFavorites")
    public List<FlightDTO> findFavorites(@RequestParam String email){
        return userService.findFavorites(email);
    }

    @DeleteMapping("/removeFavorite")
    public List<FlightDTO> removeFavorite(@RequestParam String email, @RequestParam String number) {

        return userService.removeFavorite(email, number);
    }

}
