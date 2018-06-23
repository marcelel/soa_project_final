package com.agh.edu.client.controller;

import com.agh.edu.api.dto.UserDto;
import com.agh.edu.api.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserController implements Serializable {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/UserService!com.agh.edu.api.service.UserService")
    private UserService userService;

    @Getter
    private List<UserDto> users;

    @Getter
    @Setter
    private UserDto user;

    @PostConstruct
    public void init() {
        users = userService.getUsers();
        if (!users.isEmpty()) {
            user = users.get(0);
            user.setPassword(StringUtils.EMPTY);
        }
    }

    public void changePassword() {
        user.setOldPassword(DigestUtils.sha256Hex(user.getOldPassword()));
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        userService.changePassword(user);
    }
}
