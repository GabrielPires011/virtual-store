package br.com.deusaprincess.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7307975731545645272L;

    @NotNull(message = "CPF é obrigatório")
    private Long cpf;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 255, message = "Nome deve conter entre 3 e 255 caracteres")
    private String name;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String password;

    @NotNull(message = "Data de nascimento é obrigatória")
    private Date birthdate;

    @NotBlank(message = "Telefone não pode estar em branco")
    @Pattern(regexp = "\\([1-10]\\d\\)\\d{5}-\\d{4}", message = "Telefone deve estar no formato (XX)XXXX-XXXX")
    private String phone;

    @NotNull(message = "Ativo é obrigatória")
    private boolean active;

    @NotNull(message = "Perfil é obrigatória")
    private Set<ProfileDTO> profiles;

    private Set<AddressDTO> addresses;
}
