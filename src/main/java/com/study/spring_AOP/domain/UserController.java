package com.study.spring_AOP.domain;


import lombok.RequiredArgsConstructor;
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

    @GetMapping("/user/{id}")
    public void findAll(@PathVariable int id){
        System.out.println("findid");
    }

    @PostMapping("/user")
    public void save(String username,String password,String phone){
        System.out.println("save");

    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable int id){
        System.out.println("delete");

    }

    @PutMapping("/user/{id}")
    public void update(@PathVariable int id,String password,String phone){
        System.out.println("update");

    }
}
