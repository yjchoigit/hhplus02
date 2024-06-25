package com.hhplus.hhplus02.myapplication.domain.repository;

import com.hhplus.hhplus02.myapplication.domain.entities.Users;

public interface UsersRepository {
    Users findById(Long userId);
}
