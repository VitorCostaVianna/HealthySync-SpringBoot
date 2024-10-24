package com.healthsync.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthsync.backend.models.Paciente;
import com.healthsync.backend.services.PacienteService;

@RestController
public class PacienteController{
    
    @Autowired
    private PacienteService pacienteService;

    @GetMapping(value = "/paciente/{cpf}")
    public ResponseEntity<Paciente> obterPorCpf(@PathVariable String cpf){
        Paciente paciente = this.pacienteService.obterPorCpf(cpf);
        return ResponseEntity.ok().body(paciente);
    }

    @PostMapping(value = "/paciente")
    public Paciente criar(@RequestBody Paciente p){
        return this.pacienteService.criar(p);
    }

    @PutMapping(value = "/paciente/{cpf}")
    public ResponseEntity<Void> atualizar(@RequestBody Paciente p , @PathVariable String cpf){
        p.setCpf(cpf);
        this.pacienteService.atualizar(p);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/paciente/{cpf}")
    public ResponseEntity<Void> excluir(@PathVariable String cpf){
        this.pacienteService.excluir(cpf);
        return ResponseEntity.noContent().build();
    }
}
    
