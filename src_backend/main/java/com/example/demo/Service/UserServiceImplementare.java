package com.example.demo.Service;

import com.example.demo.Repository.FlightRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.controllers.DTO.FlightDTO;
import com.example.demo.controllers.DTO.UserDTO;
import com.example.demo.controllers.mapper.FlightMapper;
import com.example.demo.controllers.mapper.UserMapper;
import com.example.demo.entity.Flight;
import com.example.demo.entity.User;
import com.example.demo.entity.User_type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImplementare implements UserService{

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;
        public UserServiceImplementare(UserRepository userRepository){
            this.userRepository = userRepository;
        }


    @Override
    public UserDTO findByEmail(String username) {
        return UserMapper.mapModelToDTO(userRepository.findByEmail(username));
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User updateUser(String email, String old_password, String new_password){
        UserDTO user = findByEmail(email);

        String old_pass = EncryptPassword.encryptPassword(old_password);
        String new_pass = EncryptPassword.encryptPassword(new_password);

        System.out.println("old pass: " + old_pass);
        System.out.println("new pass: " + new_pass);
        System.out.println("user pass: " + user.getPassword());

        if(user.getPassword().equals(old_pass)) {
            user.setPassword(new_pass);
        }
        userRepository.save(UserMapper.mapDTOToModel(user));
        return UserMapper.mapDTOToModel(user);
    }

    @Override
    public List<UserDTO> findBylogInOrNot() {
        List<User> users = (List<User>) userRepository.findAllByLogged(true);
        return users.stream().map(UserMapper::mapModelToDTO).collect(Collectors.toList());
    }

    @Override
    public String findName(String email) {
        User user = userRepository.findByEmail(email);
        String name = user.getName();

        return name;
    }

    @Override
    public UserDTO loginUser(String username, String password) {
        User user = userRepository.findByEmail(username);
        String pass = EncryptPassword.encryptPassword(password);
        System.out.println("parametru parola: " + pass);
        System.out.println("parola user: " + user.getPassword());
        if (user != null) {
            if(user.getType() == User_type.CLIENT) {
                if (user.getPassword().equals(pass)) {
                    user.setLogged(true);
                    userRepository.save(user);
                    return UserMapper.mapModelToDTO(user);
                }
            }
        }
        return null;
    }


    @Override
    public User insertUser(User user) {
        String pass = EncryptPassword.encryptPassword(user.getPassword());
        user.setPassword(pass);
        System.out.println("la inserare: " + user.getPassword());
        if(user.getEmail()== null || user.getPassword()== null || user.getName()== null)
        {
            throw new IllegalStateException("Email or password or name is null");
        }
        if (userRepository.findByEmail(user.getEmail()) != null)
        {
            throw new IllegalStateException("Email already used!");
        }

        return userRepository.save(user);
    }

    @Override
    public UserDTO logOut(String email) {
        User user = userRepository.findByEmail(email);
        user.setLogged(false);
        userRepository.save(user);
        return UserMapper.mapModelToDTO(user);
    }

    @Override
    public UserDTO loginAdmin(String username, String password) {
        User user = userRepository.findByEmail(username);
        String pass = EncryptPassword.encryptPassword(password);
        System.out.println("parametru parola: " + pass);
        System.out.println("parola user: " + user.getPassword());
        if (user != null) {
            if(user.getType() == User_type.ADMIN) {
                if (user.getPassword().equals(pass)) {
                    user.setLogged(true);
                    userRepository.save(user);
                    return UserMapper.mapModelToDTO(user);
                }
            }
        }
        return null;
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(UserMapper::mapModelToDTO).collect(Collectors.toList());
    }

    public String forgotPassword(String email){
            User user = userRepository.findByEmail(email);
            return user.getPassword();
    }

    @Override
    public List<FlightDTO> addFavorites(String email, String number) {
        User user = userRepository.findByEmail(email);
        Flight flight = flightRepository.findByNumber(number);
        //System.out.println(user.getEmail());
        if(user != null) {
            user.getFavorites().add(flight);
            userRepository.save(user);
        }
        return user.getFavorites().stream()
                .map(FlightMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }

    public List<FlightDTO> removeFavorite(String email, String number) {
        User user = userRepository.findByEmail(email);
        Flight flight = flightRepository.findByNumber(number);
        user.getFavorites().remove(flight);
        userRepository.save(user);
        for(Flight f: user.getFavorites()){
            System.out.println(f.getNumber());
        }
        //System.out.println(email + " " + number);
        return user.getFavorites().stream()
                .map(FlightMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }

    public List<FlightDTO> findFavorites(String email)
    {
        User user = userRepository.findByEmail(email);
        return user.getFavorites().stream()
                .map(FlightMapper::mapModelToDTO)
                .collect(Collectors.toList());
    }
}
