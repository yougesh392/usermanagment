package com.usmobile.usermanagment.service;

import com.usmobile.usermanagment.entity.User;
import com.usmobile.usermanagment.model.UpdateUserDTO;
import com.usmobile.usermanagment.model.UserDTO;
import com.usmobile.usermanagment.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserManagmentServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserManagementService userManagementService;

    @Test
    public void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("test");
        userDTO.setLastName("test");
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPassword("password");
        userDTO.setPhoneNumber("1234567890");

        User user = new User();
        user.setId("123");
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setPhoneNumber("1234567890");

        when(userRepository.insert((User) any())).thenReturn(user);

        UserDTO createdUser = userManagementService.createUser(userDTO);

        assertEquals(userDTO.getFirstName(), createdUser.getFirstName());
        assertEquals(userDTO.getLastName(), createdUser.getLastName());
        assertEquals(userDTO.getEmail(), createdUser.getEmail());
    }

    @Test
    public void testUpdateUser() throws Exception {
        UpdateUserDTO userDTO = new UpdateUserDTO();
        userDTO.setUserId("123");
        userDTO.setFirstName("test");
        userDTO.setLastName("test");
        userDTO.setEmail("john.doe@example.com");
        User user = new User();
        user.setId("123");
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("john.doe@example.com");

        when(userRepository.findById("123")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UpdateUserDTO updatedUser = userManagementService.updateUser(userDTO);

        assertEquals(userDTO.getFirstName(), updatedUser.getFirstName());
        assertEquals(userDTO.getLastName(), updatedUser.getLastName());
        assertEquals(userDTO.getEmail(), updatedUser.getEmail());
    }
}
