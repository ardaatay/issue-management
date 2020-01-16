package com.ardaatay.issuemanagement.repository;

import com.ardaatay.issuemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
