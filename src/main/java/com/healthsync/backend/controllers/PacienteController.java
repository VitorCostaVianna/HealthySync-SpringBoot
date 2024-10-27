package com.healthsync.backend.controllers;

import com.healthsync.backend.controllers.dto.UpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthsync.backend.models.Paciente;
import com.healthsync.backend.services.PacienteService;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class PacienteController{

    @Autowired
    private PacienteService pacienteService;

    // Método para obter todos os pacientes
    @GetMapping(value = "/paciente")
    public List<Paciente> obterTodos(){
        return this.pacienteService.obterTodos();
    }

    // Método para obter um paciente por id
    @GetMapping(value = "/paciente/id/{id}")
    public ResponseEntity<Paciente> obterPorId(@PathVariable Long id){
        Paciente paciente = this.pacienteService.obterPorId(id);
        return ResponseEntity.ok().body(paciente);
    }

    // Método para obter um paciente por cpf
    @GetMapping(value = "/paciente/cpf/{cpf}")
    public ResponseEntity<Paciente> obterPorCpf(@PathVariable String cpf){
        Paciente paciente = this.pacienteService.findByCpf(cpf);
        return ResponseEntity.ok().body(paciente);
    }

    // Método para criar um paciente
    @PostMapping(value = "/paciente")
    public Paciente criar(@RequestBody Paciente p){
        return this.pacienteService.criar(p);
    }

    // Método para atualizar um paciente pelo cpff
    @PutMapping(value = "/paciente/cpf/{cpf}")
    public ResponseEntity<Void> atualizar(@PathVariable String cpf,
                                          @RequestBody UpdateDto updateDto){
        pacienteService.updateUserByCpf(cpf, updateDto);
        return ResponseEntity.noContent().build();
    }


    // Método para excluir um paciente pelo CPF
    @DeleteMapping(value = "/paciente/cpf/{cpf}")
    public ResponseEntity<Void> excluir(@PathVariable String cpf) {
        pacienteService.excluir(cpf);  // Usando o CPF do paciente para excluir
        return ResponseEntity.noContent().build();  // Retorna o código 204 (No Content)
    }

}
