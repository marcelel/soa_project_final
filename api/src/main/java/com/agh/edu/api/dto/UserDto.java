package com.agh.edu.api.dto;

import lombok.Data;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private Integer id;
    private String login;
    private String password;
    private String oldPassword;

    public UserDto() {
    }

    @Override
    public String toString() {
        return login;
    }
}
