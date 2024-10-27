package com.healthsync.backend.services;

import com.healthsync.backend.controllers.dto.UpdateDtoFuncionario;
import com.healthsync.backend.exceptions.ResourceNotFoundException;
import com.healthsync.backend.models.Funcionario;
import com.healthsync.backend.repositories.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> obterTodos() {
        return this.funcionarioRepository.findAll();
    }

    public Funcionario findByEmail(String email){
        return this.funcionarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado"));
    }

    public Funcionario findById(Long id) {
        return this.funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado com ID: " + id));
    }

    public Funcionario findByCpf(String cpf) {
        return this.funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado com CPF: " + cpf));
    }

    public Funcionario findByUsername(String nome){
        return this.funcionarioRepository.findByName(nome)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado"));
    }

    public void delete(String cpf) {
        Funcionario funcionario = findByCpf(cpf);
        funcionarioRepository.deleteById(funcionario.getId());
    }

    @Transactional
    public void updateUserByCpf(String userCpf, UpdateDtoFuncionario updateDto) {

        Funcionario funcionario = funcionarioRepository.findByCpf(userCpf).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com CPF: " + userCpf));

        if (updateDto.nome() != null) {
            funcionario.setNome(updateDto.nome());
        }
        if (updateDto.email() != null) {
            funcionario.setEmail(updateDto.email());
        }
        if (updateDto.cidade() != null) {
            funcionario.setCidade(updateDto.cidade());
        }
        if (updateDto.telefone() != null) {
            funcionario.setTelefone(updateDto.telefone());
        }
        if (updateDto.cargo() != null) {
            funcionario.setCargo(updateDto.cargo());
        }
        if (updateDto.salario() != null) {
            funcionario.setSalario(updateDto.salario());
        }
        if (updateDto.especialidade() != null) {
            funcionario.setEspecialidade(updateDto.especialidade());
        }


        funcionarioRepository.save(funcionario);
    }

    @Transactional
    public Funcionario criar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }
}