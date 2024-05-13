package com.usmobile.usermanagment.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDTO {
    @Schema(description = "First name of the User", example = "John")
    private String firstName;
    @Schema(description = "Last name of the User", example = "Doe")
    private String lastName;
    @Schema(description = "Email of the User", example = "johnDoe@email.com")
    private String email;
    @Schema(description = "Password of the User", example = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Schema(description = "Phone number of the User", example = "1234567890")
    private String phoneNumber;
}
