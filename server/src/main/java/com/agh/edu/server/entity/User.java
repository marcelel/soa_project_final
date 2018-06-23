package com.agh.edu.server.entity;

import com.agh.edu.api.dto.Role;
import com.agh.edu.server.core.ModelObject;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Category> categories;
}
