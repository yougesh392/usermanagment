package com.usmobile.usermanagment.utils;

import com.usmobile.usermanagment.DAO.UserDAO;
import com.usmobile.usermanagment.DTO.UserDTO;

public class UserMapper {
    public static UserDAO dtoToDao(UserDTO userDTO) {
        UserDAO user = new UserDAO();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }
    public static UserDTO daoToDto(UserDAO userDAO) {
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
