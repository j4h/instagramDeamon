package com.dr3amers.repository;

import com.dr3amers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByNickname(String nickname);
}
