package br.com.deusaprincess.service;

import br.com.deusaprincess.model.UserDTO;
import jakarta.validation.Valid;
import java.util.List;

public interface UserService {

    UserDTO save(@Valid UserDTO user);

    UserDTO update(@Valid UserDTO user);

    void delete(Long cpf);

    UserDTO findByCpf(Long cpf);

    UserDTO findByEmail(String email);

    List<UserDTO> findAll();
}
