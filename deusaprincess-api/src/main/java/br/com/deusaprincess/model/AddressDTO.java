package br.com.deusaprincess.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class AddressDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5677752852540369483L;

    private String uuid;

    @NotBlank(message = "O campo 'logradouro' não pode estar em branco.")
    @Size(max = 255, message = "O tamanho máximo do campo 'logradouro' é de 255 caracteres.")
    private String street;

    @NotBlank(message = "O campo 'número' não pode estar em branco.")
    @Size(max = 20, message = "O tamanho máximo do campo 'número' é de 20 caracteres.")
    private String number;

    @NotBlank(message = "O campo 'bairro' não pode estar em branco.")
    @Size(max = 255, message = "O tamanho máximo do campo 'bairro' é de 255 caracteres.")
    private String district;

    @NotBlank(message = "O campo 'cidade' não pode estar em branco.")
    @Size(max = 255, message = "O campo 'cidade' não pode estar em branco.")
    private String city;

    @NotBlank(message = "O campo 'estado' não pode estar em branco.")
    @Size(max = 2, message = "O tamanho máximo do campo 'estado' é de 2 caracteres.")
    private String state;

    @NotBlank(message = "O campo 'CEP' não pode estar em branco.")
    @Size(min = 8, message = "'CEP' deve ter no mínimo 8 caracteres")
    private String zipCode;
}
