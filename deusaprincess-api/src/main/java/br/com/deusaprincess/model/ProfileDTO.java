package br.com.deusaprincess.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4317493142747952055L;

    @NotNull(message = "UUID é obrigatório")
    private UUID uuid;

    @NotNull(message = "Nome é obrigatório")
    private String name;

}
