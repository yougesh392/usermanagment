package com.usmobile.usermanagment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User entity")
public class User {
    @Id
    @Schema(description = "Unique identifier of the User")
    private String id;
    @Schema(description = "First name of the User", example = "John")
    private String firstName;
    @Schema(description = "Last name of the User", example = "Doe")
    private String lastName;
    @Schema(description = "Email of the User", example = "johnDoe@email.com")
    private String email;
    @Schema(description = "Password of the User", example = "password")
    private String password;
    @Schema(description = "Phone number of the User", example = "1234567890")
    private String phoneNumber;
}