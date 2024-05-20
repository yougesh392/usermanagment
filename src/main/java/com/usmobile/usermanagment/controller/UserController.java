package com.usmobile.usermanagment.controller;

import com.usmobile.usermanagment.model.UpdateUserRequest;
import com.usmobile.usermanagment.model.UserDTO;
import com.usmobile.usermanagment.service.UserManagementService;
import com.usmobile.usermanagment.utils.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "User management API")
public class UserController {
    private UserManagementService userManagementService;
    public UserController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
    })
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) throws Exception {
        UserDTO createdUser = userManagementService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update a existing user", description = "Create a new user and return the created user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))),
    })
    public ResponseEntity<UpdateUserRequest> updateUser(@RequestBody UpdateUserRequest user) throws Exception {
        UpdateUserRequest createdUser = userManagementService.updateUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    @Operation(summary = "Get a existing user", description = "return the existing user")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId) throws Exception {
        UserDTO user = userManagementService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
