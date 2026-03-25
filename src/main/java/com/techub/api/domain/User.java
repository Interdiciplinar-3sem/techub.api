package com.techub.api.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    //Status status;  para implementar hierarquia de acessos

    private String email;

    private String senha;


    // getters e setters

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   /* public Status getStatus() {return status;}
    public void setStatus(Status status) {this.status = status;}

    */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}