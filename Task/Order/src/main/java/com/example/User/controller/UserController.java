package com.example.User.controller;

import com.example.User.Service.UserService;
import com.example.User.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import  java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
   private UserService userService;








    @PostMapping("/post")
    public ResponseEntity<User> adduser(@RequestBody User user){
                User savedUser = userService.addUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getUser(){
        return  new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public  ResponseEntity<User> getUserById(@PathVariable String id){
        User user = userService.getUserById(id);
        return  new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User upadateUser){
        User user = userService.updateUser(id, upadateUser);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return new ResponseEntity<>("This User info is deleted with this id:"+id, HttpStatus.NO_CONTENT);
    }
}
