package com.techub.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_resumos")
@Getter
@Setter
public class Summary {
//POsts
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataHora;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;



}
