package com.examhub.backend.service;

import com.examhub.backend.model.User;
import com.examhub.backend.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface UserService {
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    public User getUser(String username);

    public void deleteUser(Long userId);

    public User getUserByEmail(String email);
}
