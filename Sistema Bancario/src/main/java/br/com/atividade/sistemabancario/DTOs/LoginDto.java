package br.com.atividade.sistemabancario.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDto {

    @Email(message = "Email format incorrect")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Username cannot be empty")
    private String username;


    @NotBlank(message = "Password cannort be empty")
    private String password;
}
