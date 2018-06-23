package com.agh.edu.server.adapter;

import com.agh.edu.api.dto.UserDto;
import com.agh.edu.server.entity.User;

public class UserDtoAdapter {

    public UserDto createUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
