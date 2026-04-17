package com.techub.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_alunos")
@Getter
@Setter

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private Integer semestre = 1;

    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private String foto;

    private Integer pontuacao = 0;
}