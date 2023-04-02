package br.com.deusaprincess.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7489382136413107759L;

    @NotNull(message = "E-mail é obrigatório")
    private String email;

    @NotNull(message = "Senha é obrigatório")
    private String password;
}
