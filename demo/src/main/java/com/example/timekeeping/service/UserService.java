package com.example.timekeeping.service;

import com.example.timekeeping.model.User;

import java.util.Collection;

public interface UserService{
    User create(User User);
    Collection<User> list();
    User get(Long id);
    User update(User User);
    Boolean delete(Long id);
}
