package com.ardaatay.issuemanagement.service;

import com.ardaatay.issuemanagement.dto.ProjectDto;
import com.ardaatay.issuemanagement.dto.UserDto;
import com.ardaatay.issuemanagement.entity.User;
import com.ardaatay.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {

    UserDto save(UserDto userDto);

    UserDto getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    List<UserDto> getAll();

    UserDto update(Long id, UserDto userDto);

    Boolean delete(UserDto userDto);

    Boolean deleteById(Long id);
}
