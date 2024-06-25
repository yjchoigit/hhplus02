package com.hhplus.hhplus02.myapplication.infrastructure;

import com.hhplus.hhplus02.myapplication.domain.entities.Users;
import com.hhplus.hhplus02.myapplication.domain.repository.JpaUsersRepository;
import com.hhplus.hhplus02.myapplication.domain.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepositoryImpl implements UsersRepository {
    private final JpaUsersRepository jpaUsersRepository;

    public UsersRepositoryImpl(JpaUsersRepository jpaUsersRepository) {
        this.jpaUsersRepository = jpaUsersRepository;
    }

    @Override
    public Users findById(Long userId) {
        return jpaUsersRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
