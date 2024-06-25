package com.hhplus.hhplus02.myapplication.domain.repository;

import com.hhplus.hhplus02.myapplication.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findById(Long userId);
}
