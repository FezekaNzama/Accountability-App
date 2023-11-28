package com.fezekanzama.accountable_app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.fezekanzama.accountable_app.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
