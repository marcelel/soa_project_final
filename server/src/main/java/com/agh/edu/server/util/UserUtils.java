package com.agh.edu.server.util;

import com.agh.edu.api.dto.Role;
import com.agh.edu.server.dao.UserDao;
import com.agh.edu.server.entity.User;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.security.Principal;

@ApplicationScoped
public class UserUtils {

    @Inject
    private UserDao userDao;

    public User getLoggedUser() {
        Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if (userPrincipal == null) {
            return null;
        }
        return userDao.getUserByLogin(userPrincipal.getName());
    }

    public boolean isUserAdmin() {
        return isAdmin(getLoggedUser());
    }

    public boolean isAdmin(User user) {
        return Role.Administrator.equals(user.getRole());
    }
}
