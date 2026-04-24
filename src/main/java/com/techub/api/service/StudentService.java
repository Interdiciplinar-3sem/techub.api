package com.techub.api.service;

import com.techub.api.domain.Student;
import com.techub.api.domain.User;
import com.techub.api.dto.UserLoginDataDTO;
import com.techub.api.dto.UserUpdateStudentRequestDTO;
import com.techub.api.repository.StudentRepository;
import com.techub.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public Student buscar_por_id(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public Integer obter_pontuacao(Long id){ return buscar_por_id(id).getPontuacao(); }

    public List<Student> listar() { return studentRepository.findAll(); }

    public Student buscar_perfil(Long id) { return studentRepository.findById(id).orElseThrow(() -> new RuntimeException(("Erro ao buscar usuario"))); }

    @Transactional
    public void atualizar_perfil(Long id, UserUpdateStudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        if (dto.nome() != null) {
            student.setNome(dto.nome());
        }
        if (dto.semestre() != null) {
            student.setSemestre(dto.semestre());
        }
        if (dto.bio() != null) {
            student.setBio(dto.bio());
        }
        if (dto.foto() != null) {
            student.setFoto(dto.foto());
        }

        studentRepository.save(student);
    }

    public void deletar(Long id){ studentRepository.deleteById(id);  }

    public UserLoginDataDTO obter_dados_login(Long id){
        User user = userRepository.findByStudent_Id(id)
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar estudante"));

        return new UserLoginDataDTO(user.getEmail(), user.getSenha());
    }
}
