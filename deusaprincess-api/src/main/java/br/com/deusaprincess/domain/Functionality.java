package br.com.deusaprincess.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_functionality")
public class Functionality implements Serializable {

    @Serial
    private static final long serialVersionUID = -8858697930038441974L;

    @Id
    private UUID uuid;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "method", nullable = false, length = 10)
    private String method;

    @ManyToMany(mappedBy = "functionalities")
    private List<Profile> profiles;
}

