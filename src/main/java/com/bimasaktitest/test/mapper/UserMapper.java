package com.bimasaktitest.test.mapper;


import com.bimasaktitest.test.dto.RegisterDto;
import com.bimasaktitest.test.dto.UserDto;
import com.bimasaktitest.test.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User fromRegisterDto(RegisterDto dto) {
        User user = new User();
        user.setName(dto.getNama());
        user.setAge(dto.getAge());
        user.setCity(dto.getCity());

        return user;
    }

    public RegisterDto toRegisterDto(User user) {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setNama(user.getName());
        registerDto.setAge(user.getAge());
        registerDto.setCity(user.getCity());
        return registerDto;
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setCity(user.getCity());
        return userDto;
    }
}
