package com.agh.edu.api.service;

import com.agh.edu.api.dto.UserDto;

import java.util.List;

public interface UserService {

    public List<UserDto> getUsers();

    public void changePassword(UserDto userDto);
}
