package com.example.demo.repositories;

import com.example.demo.models.LAMUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LAMUserRepository extends JpaRepository<LAMUser, UUID> {
    boolean existsByUsernameOrEmail(String username, String email);
}
