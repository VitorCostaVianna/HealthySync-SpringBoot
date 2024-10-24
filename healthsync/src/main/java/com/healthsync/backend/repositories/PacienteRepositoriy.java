package com.healthsync.backend.repositories;

import java.util.Optional;
import java.lang.String;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthsync.backend.models.Paciente;

@Repository
public interface PacienteRepositoriy extends JpaRepository<Paciente, String> {
    Optional<Paciente> findByCpf(String cpf);
}