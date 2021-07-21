package com.bimasaktitest.test.api;

import com.bimasaktitest.test.DefaultResponse;
import com.bimasaktitest.test.dto.UserDto;
import com.bimasaktitest.test.entity.User;
import com.bimasaktitest.test.repository.UserRepository;
import com.bimasaktitest.test.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class userApi {
    @Autowired
    private UserRepository userRepository;

    public ModelMapper modelMapper;

    @Autowired
    private UserServiceImpl userService;

    //User Information Get Mapping
    @GetMapping("/{name}")
    public UserDto getUser(@PathVariable String name) {
        User user = userRepository.findByName(name).get();
        UserDto userDto = new UserDto();
        modelMapper.map(user, userDto);
        userDto.setIdUser(user.getIdUser());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setCity(user.getCity());
        return userDto;
    }

}

