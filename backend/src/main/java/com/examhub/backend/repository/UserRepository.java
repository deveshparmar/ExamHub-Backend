package com.examhub.backend.repository;

import com.examhub.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);

    public User findByEmail(String email);
}
