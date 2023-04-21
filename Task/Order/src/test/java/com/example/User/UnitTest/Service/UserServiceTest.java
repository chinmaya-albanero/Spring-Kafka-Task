package com.example.User.UnitTest.Service;

import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import com.example.User.Respo.UserRepository;
import static org.junit.Assert.*;
import com.example.User.Service.UserService;
import com.example.User.controller.UserController;
import com.example.User.model.User;


import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class UserServiceTest {

    @Autowired
   private UserService userService;

    @Autowired
    private UserController userController;

    @MockBean
    UserRepository userRepository;
    private User userDetails = new User("1","Chinmaya","email@email.com","password");




    @BeforeEach
    public void mockFun() {
        when(userRepository.save(userDetails)).thenReturn(userDetails);
    }


    @Test
    public void testAddUser() {
        Assertions.assertEquals(userService.addUser(userDetails),userDetails);

// Invalid Test case
        Assertions.assertEquals(userService.addUser(null),null);

    }

    @Test
    public void testGetAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("1", "John", "john@gmail.com", "password"));
        userList.add(new User("2", "Jane", "jane@gmail.com", "password"));
        when(userRepository.findAll()).thenReturn(userList);
        List<User> allUsers = userService.getAllUser();
        Assertions.assertEquals(userList, allUsers);
    }


    @Test
    public void testGetUserById() {
        User user = new User("1", "John", "john@gmail.com", "password");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        User retrievedUser = userService.getUserById("1");
        Assertions.assertEquals(user, retrievedUser);
    }

    @Test
    public void testUpdateUser() {
        User user = new User("1", "John", "john@gmail.com", "password");
        User updatedUser = new User("1", "John Doe", "johndoe@gmail.com", "newpassword");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);
        User savedUser = userService.updateUser("1", updatedUser);
        Assertions.assertEquals(updatedUser, savedUser);
    }

}