package com.usmobile.usermanagment.controller;

import com.usmobile.usermanagment.model.UpdateUserRequest;
import com.usmobile.usermanagment.model.UserDTO;
import com.usmobile.usermanagment.service.UserManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserManagementService userManagementService;
    /*
    @Test - for the success scenario 201 Created
    * */
    @Test
    public void testCreateUser() throws Exception {
        UserDTO user = new UserDTO();
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        when(userManagementService.createUser(any())).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isCreated());
    }
    /*
    * @Test - for the duplicate entry scenario 422 Unprocessable Entity
    * */
    @Test
    public void testCreateUserDuplicate() throws Exception {
        when(userManagementService.createUser(any()))
                .thenThrow(new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Record already exists"));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    public void testUpdateUser() throws Exception {
        UpdateUserRequest user = new UpdateUserRequest();
        user.setUserId("123");
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("john.doe@example.com");

        when(userManagementService.updateUser(any())).thenReturn(user);

        mockMvc.perform(put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"123\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdateUserNotFound() throws Exception {
        when(userManagementService.updateUser(any()))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        mockMvc.perform(put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"123\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isNotFound());
    }

}
