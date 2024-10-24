package com.healthsync.backend.services;

import org.springframework.stereotype.Service;

import com.healthsync.backend.models.Paciente;
import com.healthsync.backend.repositories.PacienteRepositoriy;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class PacienteService{
    @Autowired
    private PacienteRepositoriy pacienteRepositoriy;

    public List<Paciente> obterTodos() {
        return pacienteRepositoriy.findAll(); 
    }

    public Paciente obterPorCpf(String cpf) {
        Optional<Paciente> paciente = this.pacienteRepositoriy.findByCpf(cpf);
        return paciente.orElseThrow(() -> new RuntimeException(
            "Paciente não encontrado!"
        ));
    }

    @Transactional
    public Paciente criar(Paciente paciente){
        return pacienteRepositoriy.save(paciente);
    }

    @Transactional
    public Paciente atualizar(Paciente newPaciente){
        Paciente existingPaciente = obterPorCpf(newPaciente.getCpf());
        
        existingPaciente.setNome(newPaciente.getNome());
        existingPaciente.setEmail(newPaciente.getEmail());
        existingPaciente.setCep(newPaciente.getCep());
        existingPaciente.setRua(newPaciente.getRua());
        existingPaciente.setCidade(newPaciente.getCidade());
        existingPaciente.setComplemento(newPaciente.getComplemento());
        existingPaciente.setNumero(newPaciente.getNumero());
        
        return this.pacienteRepositoriy.save(existingPaciente);
    }

    
    public void excluir(String cpf){
        obterPorCpf(cpf);
        try {
            pacienteRepositoriy.deleteById(cpf);
        } catch (Exception e) {
           throw new RuntimeException("Não foi posível excluir o paciente!");
        }
    }

}