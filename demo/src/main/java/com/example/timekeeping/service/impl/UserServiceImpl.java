package com.example.timekeeping.service.impl;

import com.example.timekeeping.model.User;
import com.example.timekeeping.repo.UserRepo;
import com.example.timekeeping.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User create(User User) {
        return userRepo.save(User);
    }

    @Override
    public Collection<User> list() {
        return userRepo.findAll().stream().toList();
    }

    @Override
    public User get(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User update(User User) {
        return userRepo.save(User);
    }

    @Override
    public Boolean delete(Long id) {
        userRepo.deleteById(id);
        return true;
    }
}
