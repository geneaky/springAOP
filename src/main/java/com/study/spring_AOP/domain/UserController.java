package com.study.spring_AOP.domain;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public CommonDto<?> save(@Valid @RequestBody JoinReqDto user,BindingResult bindingResult){
        System.out.println("user = " + user);
        return new CommonDto<>(200,"ok");
    }

    @DeleteMapping("/user/{id}")
    public CommonDto delete(@PathVariable int id){
        System.out.println("delete");
        userRepository.delete();
        return new CommonDto(HttpStatus.OK.value(),null);
    }

    @PutMapping("/user/{id}")
    public void update(@PathVariable int id,String password,String phone){
        System.out.println("update");

    }

    @GetMapping("/user/{id}")
    public CommonDto<User> findById(@PathVariable int id){
        return new CommonDto<>(HttpStatus.OK.value(), userRepository.findById(id));
    }
}
