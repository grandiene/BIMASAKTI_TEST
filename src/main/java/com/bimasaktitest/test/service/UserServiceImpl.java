package com.bimasaktitest.test.service;

import com.bimasaktitest.test.dto.RegisterDto;
import com.bimasaktitest.test.dto.UserDto;

import com.bimasaktitest.test.entity.User;
import com.bimasaktitest.test.getInput;
import com.bimasaktitest.test.mapper.UserMapper;
import com.bimasaktitest.test.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Scanner;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public RegisterDto saveRegister(RegisterDto registerDto) {
        registerDto.setNama(registerDto.getNama().toUpperCase());
        registerDto.setCity(registerDto.getCity().toUpperCase());
        User user = userMapper.fromRegisterDto(registerDto);
        registerDto = userMapper.toRegisterDto(userRepository.save(user));
        return registerDto;
    }

    @Override
    public User saveUserMaterDetail(User user) {
        return null;
    }
}
