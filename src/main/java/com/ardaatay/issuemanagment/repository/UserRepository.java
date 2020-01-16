package com.ardaatay.issuemanagment.repository;

import com.ardaatay.issuemanagment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
