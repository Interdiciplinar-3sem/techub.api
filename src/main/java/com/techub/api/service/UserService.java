package com.techub.api.service;

import com.techub.api.domain.Role;
import com.techub.api.domain.Student;
import com.techub.api.domain.User;
import com.techub.api.dto.UserCreateStudentRequestDTO;
import com.techub.api.dto.UserRoleResponse;
import com.techub.api.dto.UserLoginDataDTO;
import com.techub.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User criarUsuario(User user) {

        // valida email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        // hash da senha
        String senhaHash = passwordEncoder.encode(user.getSenha());
        user.setSenha(senhaHash);

        // salva
        return userRepository.save(user);
    }

    @Transactional
    public User cadastrarAluno(UserCreateStudentRequestDTO dto){
        User user = new User();
        user.setEmail(dto.email());
        user.setSenha(dto.senha());

        Student student = new Student();
        student.setNome(dto.nome());
        student.setBio(dto.bio());
        student.setFoto(dto.foto());

        user.setStudent(student);
        user.setRole(Role.Aluno);

        return criarUsuario(user);
    }

    public void atualizar_dados_login(Long id, UserLoginDataDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("Erro ao buscar usuario!")));

        if(dto.email() != null) { user.setEmail(dto.email()); }
        if(dto.senha() != null) { user.setSenha(dto.senha()); }

        userRepository.save(user);
    }

    public List<User> listar() {
        return userRepository.findAll();
    }

    public Optional<User> buscar_por_id(Long id) {
        return userRepository.findById(id);
    }

    public void deletar(Long id) {
        userRepository.deleteById(id);
    }

    public UserRoleResponse descobrirRole(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(("Erro ao buscar usuario!")));

        if(user.getStudent() != null) {
            return new UserRoleResponse(user.getStudent().getId(), user.getRole());
        }
        throw new RuntimeException("Não foi possivel encontrar o role");
    }
}
