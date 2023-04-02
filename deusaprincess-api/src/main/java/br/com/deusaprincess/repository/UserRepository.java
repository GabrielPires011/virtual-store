package br.com.deusaprincess.repository;

import br.com.deusaprincess.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(Long cpf);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}