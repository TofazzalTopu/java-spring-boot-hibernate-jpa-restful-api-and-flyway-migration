package com.user.progress.service.impl;

import com.user.progress.constant.AppConstants;
import com.user.progress.exceptions.AlreadyExistException;
import com.user.progress.exceptions.NotFoundException;
import com.user.progress.model.User;
import com.user.progress.repository.UserRepository;
import com.user.progress.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        log.info("\nSaving user {}", user);
        if (findByName(user.getName()).isPresent())
            throw new AlreadyExistException(AppConstants.USER_NAME_ALREADY_EXIST);
        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User user) {
        log.info("\nUpdating user by id {}", userId);
        Optional<User> optionalUser = findByName(user.getName());
        if (optionalUser.isPresent() && !optionalUser.get().getId().equals(userId))
            throw new AlreadyExistException(AppConstants.USER_NAME_ALREADY_EXIST);

        User savedUser = findById(userId);
        savedUser.setName(user.getName());
        savedUser.setCountry(user.getCountry());
        return userRepository.save(savedUser);
    }

    @Override
    public User updatePartial(Long userId, String country) {
        User savedUser = findById(userId);
        savedUser.setCountry(country);
        return userRepository.save(savedUser);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(AppConstants.USER_NOT_FOUND));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByName(String name) {
        log.info("\nFind user by name {}", name);
        return userRepository.findByName(name);
    }
}
