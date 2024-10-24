package com.healthsync.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @Column(name = "pac_cpf", unique = true)
    private String cpf;

    @Column(name = "pac_nome")
    private String nome;

    @Column(name = "pac_email", unique = true)
    private String email;

    @Column(name = "pac_cep")
    private String cep;

    @Column(name = "pac_rua")
    private String rua;

    @Column(name= "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String Complemento;

    @Column(name = "cidade")
    private String cidade;


    public Paciente(){

    }

    public Paciente(String cpf, String nome, String email, String cep, String rua, Integer numero, String complemento,
            String cidade) {
        this.setCpf(cpf);
        this.setNome(nome);
        this.setEmail(email);
        this.setCep(cep);
        this.setRua(rua);
        this.setNumero(numero);
        this.setComplemento(complemento);
        this.setCidade(cidade);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String complemento) {
        Complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


}
