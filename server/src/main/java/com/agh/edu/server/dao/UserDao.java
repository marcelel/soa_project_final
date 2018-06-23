package com.agh.edu.server.dao;

import com.agh.edu.server.core.AbstractDao;
import com.agh.edu.server.core.EMF;
import com.agh.edu.server.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class UserDao extends AbstractDao<User> {

    public UserDao() {
        setClazz(User.class);
    }

    public User getUserByLogin(String login) {
        entityManager = EMF.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("from User u where u.login=:login", User.class);
        query.setParameter("login", login);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }
    }
}
