package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserServiceImplementare;
import com.example.demo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private User user;

    private UserServiceImplementare userService;

    @BeforeEach
    void init() {
       initMocks(this);
       user = new User();
       user.setName("Ioneasa");
       user.setEmail("cristina.ioneasa");
       when(userRepository.findByEmail("cristina.ioneasa")).thenReturn(user);
    }

/*
    @Test
    void updateUser(){
        user = new User();
        user.setName("Ioneasa");
        user.setUsername("cristina.ioneasa");
        userService = new UserServiceImplementare(userRepository);
        User user2 = userService.updateUser(user, "new_pass");
        assertEquals(user2.getPassword(), "new_pass");
    }
*/
    /*
    @Test
    void findByUsernameWhenUserExist(){
        userService = new UserServiceImplementare(userRepository);
        User user1 = userService.findByUserame("cristina.ioneasa");
        assertNotNull(user1);
    }
*/
    /*
    @Test
    void findByUsernameWhenUserNotExist(){
        when(userRepository.findByEmail("non_existing_user")).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () ->
                userService.findByUserame("non_existing_user"));
    }

     */
}