package com.bimasaktitest.test.service;

import com.bimasaktitest.test.dto.RegisterDto;
import com.bimasaktitest.test.dto.UserDto;
import com.bimasaktitest.test.entity.User;

public interface UserService {
    public RegisterDto saveRegister(RegisterDto registerDto);
    User saveUserMaterDetail(User user);
}
