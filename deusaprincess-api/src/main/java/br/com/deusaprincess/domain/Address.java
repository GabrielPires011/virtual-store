package br.com.deusaprincess.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_address")
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 8378942659468536595L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false, length = 10)
    private String number;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "zipcode", nullable = false, length = 8)
    private String zipCode;
}
