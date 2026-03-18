package br.com.fiap.techchallenge.auth.infra.persistence.repository;
import br.com.fiap.techchallenge.auth.infra.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository  extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}