package com.example.User.Service;

import com.example.User.Respo.UserRepository;
import com.example.User.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.example.demo.exception.UserAlreadyExistsException;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public User addUser(User user){
        userRepository.save(user);
        return user;
    }
public List<User> getAllUser(){
        return userRepository.findAll();
}

public  User getUserById(String id){
        return  userRepository.findById(id).get();
}
public User updateUser(String id , User updateUser){

        User user = userRepository.findById(id).get();
        if(user ==  null){
            return null;
        }
        user.setId(updateUser.getId());
        user.setName(updateUser.getName());
        user.setEmail(updateUser.getEmail());
        user.setPassword(updateUser.getPassword());
return userRepository.save(user);
}

public void deleteUser(String id){
    userRepository.deleteById(id);
}
}
