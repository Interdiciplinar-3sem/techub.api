package com.techub.api.controller;

import com.techub.api.domain.Student;
import com.techub.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/pontuacao/{id}")
    public Integer obterPontuacao(@PathVariable Long id){ return studentService.obter_pontuacao(id); }

    @GetMapping
    public List<Student> listar_alunos(){
        return studentService.listar();
    }
}
