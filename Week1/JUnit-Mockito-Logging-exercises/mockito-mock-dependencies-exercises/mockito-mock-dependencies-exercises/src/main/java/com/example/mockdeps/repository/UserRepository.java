package com.example.mockdeps.repository;

import com.example.mockdeps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
