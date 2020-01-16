package com.ardaatay.issuemanagement.service;

import com.ardaatay.issuemanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    User save(User user);

    User getById(Long id);

    Page<User> getAllPageable(Pageable pageable);

    Boolean delete(User user);
}
