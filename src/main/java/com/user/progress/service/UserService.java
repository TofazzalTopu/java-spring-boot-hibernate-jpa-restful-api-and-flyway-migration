package com.user.progress.service;

import com.user.progress.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);
    User update(Long userId, User user);
    User updatePartial(Long userId, String country);
    User findById(Long id);
    List<User> findAll();
    Optional<User> findByName(String name);
}
