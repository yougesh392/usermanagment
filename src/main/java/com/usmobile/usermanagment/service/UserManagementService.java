package com.usmobile.usermanagment.service;

import com.usmobile.usermanagment.DAO.UserDAO;
import com.usmobile.usermanagment.DTO.UpdateUserDTO;
import com.usmobile.usermanagment.DTO.UserDTO;
import com.usmobile.usermanagment.repository.UserRepository;
import com.usmobile.usermanagment.utils.EncryptionUtil;
import com.usmobile.usermanagment.utils.UserMapper;
import com.usmobile.usermanagment.utils.ValidationUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


@Service
public class UserManagementService {
    private final UserRepository userRepository;

    UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDTO createUser(UserDTO user) throws Exception{
        UserDAO userDAO;

        ValidationUtil.validateUser(user);
        try {
            userDAO = UserMapper.dtoToDao(user);
            userDAO.setPassword(EncryptionUtil.encode(user.getPassword()));
            userRepository.insert(userDAO);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Record already exists");
        }
        return user;
    }
    public UpdateUserDTO updateUser(UpdateUserDTO user) throws Exception{
        ValidationUtil.validateUpdateUser(user);
        Optional<UserDAO> optionalUserDAO = userRepository.findById(user.getUserId());
        if (optionalUserDAO.isPresent()) {
            UserDAO existingUser = optionalUserDAO.get();
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
        Optional<UserDAO> optionalUserDAO = userRepository.findById(userId);
        if (optionalUserDAO.isPresent()) {
            UserDAO existingUser = optionalUserDAO.get();
            return UserMapper.daoToDto(existingUser);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }


}
