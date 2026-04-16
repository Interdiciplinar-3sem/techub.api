package com.techub.api.service;

import com.techub.api.domain.Student;
import com.techub.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> buscar_por_id(Long id) {
        return studentRepository.findById(id);
    }

    public Integer obter_pontuacao(Long id){
        return buscar_por_id(id)
                .map(Student::getPontuacao)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
    public List<Student> listar() {
        return studentRepository.findAll();
    }

}
