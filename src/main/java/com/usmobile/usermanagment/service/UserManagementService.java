package com.usmobile.usermanagment.service;

import com.usmobile.usermanagment.model.User;
import com.usmobile.usermanagment.repository.UserRepository;
import com.usmobile.usermanagment.utils.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {
    private final UserRepository userRepository;

    UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(User user) {
        ValidationUtil.validateUser(user);
        return userRepository.save(user);

    }
}
