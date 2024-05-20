package com.usmobile.usermanagment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {
    @Schema(description = "Id of the User", example = "123")
    private String userId;
    @Schema(description = "First name of the User", example = "John")
    private String firstName;
    @Schema(description = "Last name of the User", example = "Doe")
    private String lastName;
    @Schema(description = "Email of the User", example = "johnDoe@email.com")
    private String email;
}
