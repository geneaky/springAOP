package com.study.spring_AOP.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,"dsf","1234","123123213"));
        users.add(new User(3,"dsf","1234","123123213"));
        users.add(new User(4,"dsf","1234","123123213"));
        return users;
    }

    public User findById(int id){
        return new User(1,"ssar","1234","0100020230");
    }

    public void delete() {
        throw new IllegalArgumentException("nonono");
    }
}
