package com.usmobile.usermanagment.utils;

import com.usmobile.usermanagment.entity.User;
import com.usmobile.usermanagment.model.UserDTO;

public class UserMapper {
    public static User dtoToDao(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }
    public static UserDTO daoToDto(User userDAO) {
        UserDTO user = new UserDTO();
        user.setUserId(userDAO.getId());
        user.setFirstName(userDAO.getFirstName());
        user.setLastName(userDAO.getLastName());
        user.setEmail(userDAO.getEmail());
        user.setPassword(userDAO.getPassword());
        user.setPhoneNumber(userDAO.getPhoneNumber());
        return user;
    }
}
