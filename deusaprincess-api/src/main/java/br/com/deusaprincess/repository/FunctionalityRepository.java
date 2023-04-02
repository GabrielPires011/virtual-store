package br.com.deusaprincess.repository;

import br.com.deusaprincess.domain.Functionality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FunctionalityRepository extends JpaRepository<Functionality, UUID> {
}
