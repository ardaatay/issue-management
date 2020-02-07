package com.ardaatay.issuemanagement.service.impl;

import com.ardaatay.issuemanagement.dto.UserDto;
import com.ardaatay.issuemanagement.entity.User;
import com.ardaatay.issuemanagement.repository.UserRepository;
import com.ardaatay.issuemanagement.service.UserService;
import com.ardaatay.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto save(UserDto userDto) {
        if (userDto.getNameSurname() == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        User userCheck = userRepository.getOne(userDto.getId());
        if (userCheck != null) {
            throw new IllegalArgumentException("User Already Exist");
        }

        User user = modelMapper.map(userDto, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.getOne(id);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public TPage<UserDto> getAllPageable(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        TPage<UserDto> response = new TPage<>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), UserDto[].class)));
        return response;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> data = userRepository.findAll();
        return Arrays.asList(modelMapper.map(data, UserDto[].class));
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        User userDb = userRepository.getOne(id);
        if (userDb == null) {
            throw new IllegalArgumentException("User Does Not Exist ID: " + id);
        }

        userDb.setNameSurname(userDto.getNameSurname());
        userDb.setEmail(userDto.getEmail());
        userRepository.save(userDb);

        return modelMapper.map(userDb, UserDto.class);
    }

    @Override
    public Boolean delete(UserDto userDto) {
        userRepository.deleteById(userDto.getId());
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteById(Long id) {
        userRepository.deleteById(id);
        return Boolean.TRUE;
    }

}
