package com.who.shuk.JWTAuth.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    @NotEmpty(message = "First Name is mandatory")
    @NotBlank(message = "Please remove any blank spaces")
    private String firstName;
    @NotEmpty(message = "Last Name is mandatory")
    @NotBlank(message = "Please remove any blank spaces")
    private String lastName;
    @Email(message = "Email is not formatted")
    @NotEmpty(message = "Email Name is mandatory")
    @NotBlank(message = "Please remove any blank spaces")
    private String email;
    @NotEmpty(message = "Password Name is mandatory")
    @NotBlank(message = "Please remove any blank spaces")
    @Size(min = 8, message = "Password should be minimum 8 characters long")
    private String password;
}
