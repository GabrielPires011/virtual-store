package br.com.deusaprincess.controller;

import br.com.deusaprincess.exception.NotFoundException;
import br.com.deusaprincess.model.UserDTO;
import br.com.deusaprincess.serviceimpl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) throws NotFoundException {
        UserDTO createdUser = userServiceImpl.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UserDTO> getUserByCpf(@PathVariable Long cpf) throws NotFoundException {
        UserDTO user = userServiceImpl.findByCpf(cpf);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) throws NotFoundException {
        UserDTO user = userServiceImpl.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDTO) throws NotFoundException {
        UserDTO updatedUser = userServiceImpl.update(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long cpf) throws NotFoundException {
        userServiceImpl.delete(cpf);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userServiceImpl.findAll();
        return ResponseEntity.ok(users);
    }

}





