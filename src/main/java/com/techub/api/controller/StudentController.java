package com.techub.api.controller;

import com.techub.api.domain.Student;
import com.techub.api.dto.UserLoginDataDTO;
import com.techub.api.dto.UserUpdateStudentRequestDTO;
import com.techub.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/pontuacao/{id}")
    public Integer obterPontuacao(@PathVariable Long id){ return studentService.obter_pontuacao(id); }

    @PutMapping("/{id}")
    public String atualizarPerfil(@PathVariable Long id, @RequestBody UserUpdateStudentRequestDTO dto){
        studentService.atualizar_perfil(id, dto);
        return "Dados atualizado com sucesso!";
    }

    @GetMapping
    public List<Student> listarAlunos(){
        return studentService.listar();
    }

    @GetMapping("/{id}")
    public Student busarPerfil(@PathVariable Long id) { return studentService.buscar_perfil(id); }

    // ainda não está apagando por conta do cascade
    @DeleteMapping("/{id}")
    public String deleter_student(@PathVariable Long id){
        studentService.deletar(id);
        return "Dados apagados com sucesso!";
    }

    @GetMapping("/loginData/{id}")
    public UserLoginDataDTO obterDadosLogin(@PathVariable Long id){
        return studentService.obter_dados_login(id);
    }
}
