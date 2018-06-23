package com.agh.edu.server.service;

import com.agh.edu.api.dto.UserDto;
import com.agh.edu.server.adapter.UserDtoAdapter;
import com.agh.edu.server.dao.UserDao;
import com.agh.edu.server.entity.User;
import com.agh.edu.server.util.UserUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Remote(com.agh.edu.api.service.UserService.class)
@Stateless
public class UserService implements com.agh.edu.api.service.UserService {

    @Inject
    private UserUtils userUtils;

    @Inject
    private UserDao userDao;

    private UserDtoAdapter userDtoAdapter;

    public UserService() {
        userDtoAdapter = new UserDtoAdapter();
    }

    @Override
    public List<UserDto> getUsers() {
        User loggedUser = userUtils.getLoggedUser();
        if (userUtils.isAdmin(loggedUser)) {
            return userDao.getAllEntities().stream()
                    .map(x -> userDtoAdapter.createUserDto(x))
                    .collect(Collectors.toList());
        } else {
            return Collections.singletonList(userDtoAdapter.createUserDto(loggedUser));
        }
    }

    @Override
    public void changePassword(UserDto userDto) {
        User user = userDao.getById(userDto.getId());
        if (user != null && user.getPassword().equals(userDto.getOldPassword())) {
            user.setPassword(userDto.getPassword());
            userDao.update(user);
        }
    }
}
