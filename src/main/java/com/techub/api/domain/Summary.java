package com.techub.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_resumos")
@Getter
@Setter
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String conteudo;

    @Column
    private Integer reports = 0;

    @Column
    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
