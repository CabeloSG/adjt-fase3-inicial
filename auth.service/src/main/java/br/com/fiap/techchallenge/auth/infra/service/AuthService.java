package br.com.fiap.techchallenge.auth.infra.service;

import br.com.fiap.techchallenge.auth.domain.model.User;
import br.com.fiap.techchallenge.auth.domain.enums.Role;
import br.com.fiap.techchallenge.auth.domain.exception.UsuarioJaExisteException;
import br.com.fiap.techchallenge.auth.infra.persistence.entity.UserJpaEntity;
import br.com.fiap.techchallenge.auth.infra.persistence.mapper.UserMapper;
import br.com.fiap.techchallenge.auth.infra.persistence.repository.UserJpaRepository;
import br.com.fiap.techchallenge.auth.infra.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UserJpaRepository userJpaRepository,
                       PasswordEncoder encoder,
                       JwtService jwtService) {

        this.userJpaRepository = userJpaRepository;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public void register(User user) {

        // verifica se email já existe
        if (userJpaRepository.existsByEmail(user.getEmail())) {
            throw new UsuarioJaExisteException("Usuário já cadastrado com este email");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.CLIENTE);

        UserJpaEntity entity = UserMapper.toJpa(user);

        userJpaRepository.save(entity);
    }

    public String login(String email, String password) {

        UserJpaEntity entity = userJpaRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        User user = UserMapper.toDomain(entity);

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.generateToken(user);
    }
}