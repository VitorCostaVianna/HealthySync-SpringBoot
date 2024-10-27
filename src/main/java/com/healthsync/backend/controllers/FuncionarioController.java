package com.healthsync.backend.controllers;


import com.healthsync.backend.controllers.dto.CreatDtoFuncionario;
import com.healthsync.backend.models.Funcionario;
import com.healthsync.backend.models.Paciente;
import com.healthsync.backend.models.Role;
import com.healthsync.backend.repositories.FuncionarioRepository;
import com.healthsync.backend.repositories.RoleRepository;
import com.healthsync.backend.services.FuncionarioService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
public class FuncionarioController {

    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final FuncionarioService funcionarioService;

    public FuncionarioController(BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, FuncionarioService funcionarioService) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/funcionario")
    public ResponseEntity<List<Funcionario>> listFuncionarios(){
        var funcionarios = this.funcionarioService.obterTodos();
        return ResponseEntity.ok().body(funcionarios);
    }

    @GetMapping(value = "/funcionario/id/{id}")
    public ResponseEntity<Funcionario> obterPorId(@PathVariable Long id){
        Funcionario funcionario = this.funcionarioService.findById(id);
        return ResponseEntity.ok().body(funcionario);
    }

    @GetMapping(value = "/funcionario/cpf/{cpf}")
    public ResponseEntity<Funcionario> obterPorCpf(@PathVariable String cpf){
        Funcionario funcionario = this.funcionarioService.findByCpf(cpf);
        return ResponseEntity.ok().body(funcionario);
    }

    @Transactional
    @PostMapping("/funcionario")
    public ResponseEntity<Void> createFuncionario(@RequestBody CreatDtoFuncionario dto){

        var basicRole = this.roleRepository.findByName(Role.Values.BASIC.name());

        var funcionarioFromDb = this.funcionarioService.findByEmail(dto.email());

        var funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setPassword(passwordEncoder.encode(dto.password()));
        funcionario.setRoles(Set.of(basicRole));

        funcionarioService.criar(funcionario);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/funcionario/cpf/{cpf}")
    public ResponseEntity<Void> excluir(@PathVariable String cpf) {
        this.funcionarioService.delete(cpf);  // Usando o CPF do paciente para excluir
        return ResponseEntity.noContent().build();  // Retorna o código 204 (No Content)
    }


}
