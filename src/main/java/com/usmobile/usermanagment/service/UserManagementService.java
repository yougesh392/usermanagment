package com.usmobile.usermanagment.service;

import com.usmobile.usermanagment.entity.User;
import com.usmobile.usermanagment.model.BillingCycleRequest;
import com.usmobile.usermanagment.model.UpdateUserRequest;
import com.usmobile.usermanagment.model.UserDTO;
import com.usmobile.usermanagment.repository.UserRepository;
import com.usmobile.usermanagment.service.external.BillingCycleService;
import com.usmobile.usermanagment.utils.EncryptionUtil;
import com.usmobile.usermanagment.utils.UserMapper;
import com.usmobile.usermanagment.utils.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


@Service
public class UserManagementService {
    private static final Logger log = LoggerFactory.getLogger(BillingCycleService.class);
    private final UserRepository userRepository;
    private final BillingCycleService billingCycleService;

    UserManagementService(UserRepository userRepository, BillingCycleService billingCycleService) {
        this.userRepository = userRepository;
        this.billingCycleService = billingCycleService;
    }
    public UserDTO createUser(UserDTO user) throws Exception{
        User userDAO;

        ValidationUtil.validateUser(user);
        try {
            userDAO = UserMapper.dtoToDao(user);
            userDAO.setPassword(EncryptionUtil.encode(user.getPassword()));
            userRepository.insert(userDAO);
            log.debug("User created successfully");
            callBillingCycleManagement(userDAO);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Record already exists");
        }
        return user;
    }
    @Async
    private void callBillingCycleManagement(User user) {
        BillingCycleRequest billcycle = new BillingCycleRequest();
        billcycle.setUserId(user.getId());
        billcycle.setPhoneNumber(user.getPhoneNumber());
        billingCycleService.callBillingCycleManagement(billcycle);
    }
    public UpdateUserRequest updateUser(UpdateUserRequest user) throws Exception{
        ValidationUtil.validateUpdateUser(user);
        Optional<User> optionalUserDAO = userRepository.findById(user.getUserId());
        if (optionalUserDAO.isPresent()) {
            User existingUser = optionalUserDAO.get();
            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            userRepository.save(existingUser);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }
    /*
    * checks if user exists and returns the user
    * */
    public UserDTO getUser(String userId) throws Exception{
        Optional<User> optionalUserDAO = userRepository.findById(userId);
        if (optionalUserDAO.isPresent()) {
            User existingUser = optionalUserDAO.get();
            return UserMapper.daoToDto(existingUser);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }


}
