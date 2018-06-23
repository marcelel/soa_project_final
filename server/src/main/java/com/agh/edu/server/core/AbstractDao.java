package com.agh.edu.server.core;

import com.agh.edu.server.entity.Category;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractDao<T extends ModelObject> {

    private Class<T> clazz;

    protected EntityManager entityManager;

    @PostConstruct
    public void init() {
        entityManager = EMF.createEntityManager();
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> getAllEntities() {
        entityManager = EMF.createEntityManager();
        List<T> entities = entityManager.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
        entityManager.close();
        return entities;
    }

    public T getById(int id) {
        entityManager = EMF.createEntityManager();
        T entity = entityManager.find(clazz, id);
        entityManager.close();
        return entity;
    }

    public void save(T entity) {
        entityManager = EMF.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
        entityManager.close();
    }

    public void update(T entity) {
        entityManager = EMF.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(entity);
        transaction.commit();
        entityManager.close();
    }

    public void delete(T entity) {
        entityManager = EMF.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createQuery("delete from " + clazz.getSimpleName() + " e where e.id=:entityId")
                .setParameter("entityId", entity.getId())
                .executeUpdate();
        transaction.commit();
        entityManager.close();
    }
}