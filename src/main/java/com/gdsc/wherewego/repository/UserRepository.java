package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
