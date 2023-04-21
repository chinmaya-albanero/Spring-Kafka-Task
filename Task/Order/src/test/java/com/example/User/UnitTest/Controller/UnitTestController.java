//package com.example.User.UnitTest.Controller;
//
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testAddUser() {
//        User user = new User("1", "John", "john@gmail.com", "password");
//        when(userRepository.save(user)).thenReturn(user);
//        User savedUser = userService.addUser(user);
//        assertEquals(user, savedUser);
//    }
//
//    @Test
//    public void testGetAllUser() {
//        List<User> userList = new ArrayList<>();
//        userList.add(new User("1", "John", "john@gmail.com", "password"));
//        userList.add(new User("2", "Jane", "jane@gmail.com", "password"));
//        when(userRepository.findAll()).thenReturn(userList);
//        List<User> allUsers = userService.getAllUser();
//        assertEquals(userList, allUsers);
//    }
//
//    @Test
//    public void testGetUserById() {
//        User user = new User("1", "John", "john@gmail.com", "password");
//        when(userRepository.findById("1")).thenReturn(Optional.of(user));
//        User retrievedUser = userService.getUserById("1");
//        assertEquals(user, retrievedUser);
//    }
//
//    @Test
//    public void testUpdateUser() {
//        User user = new User("1", "John", "john@gmail.com", "password");
//        User updatedUser = new User("1", "John Doe", "johndoe@gmail.com", "newpassword");
//        when(userRepository.findById("1")).thenReturn(Optional.of(user));
//        when(userRepository.save(user)).thenReturn(updatedUser);
//        User savedUser = userService.updateUser("1", updatedUser);
//        assertEquals(updatedUser, savedUser);
//    }
