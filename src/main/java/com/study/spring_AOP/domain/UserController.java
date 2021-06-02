package com.study.spring_AOP.domain;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


    @GetMapping("/user")
    public List<User> findAll(){
        System.out.println("findAll");
        return userRepository.getUsers();
    }

    @PostMapping("/user")
    public ResponseEntity<String> save(@RequestBody User user){
//        System.out.println("save");
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(phone);
        System.out.println("user = " + user);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable int id){
        System.out.println("delete");

    }

    @PutMapping("/user/{id}")
    public void update(@PathVariable int id,String password,String phone){
        System.out.println("update");

    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable int id){
        return userRepository.findById(id);
    }
}
