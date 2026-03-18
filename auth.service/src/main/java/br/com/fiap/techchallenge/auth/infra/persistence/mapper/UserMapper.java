package br.com.fiap.techchallenge.auth.infra.persistence.mapper;

import br.com.fiap.techchallenge.auth.domain.model.User;
import br.com.fiap.techchallenge.auth.infra.persistence.entity.UserJpaEntity;

public class UserMapper {

    public static User toDomain(UserJpaEntity entity) {

        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole()
        );
    }

    public static UserJpaEntity toJpa(User user) {

        return new UserJpaEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}