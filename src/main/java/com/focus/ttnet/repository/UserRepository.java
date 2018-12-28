package com.focus.ttnet.repository;

import com.focus.ttnet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
