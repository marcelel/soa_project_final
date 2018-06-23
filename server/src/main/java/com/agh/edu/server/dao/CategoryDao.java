package com.agh.edu.server.dao;

import com.agh.edu.server.core.AbstractDao;
import com.agh.edu.server.core.EMF;
import com.agh.edu.server.entity.Category;
import com.agh.edu.server.entity.Element;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class CategoryDao extends AbstractDao<Category> {

    public CategoryDao() {
        setClazz(Category.class);
    }

    public List<Category> getCategoriesByCategoryTypeId(Integer id) {
        entityManager = EMF.createEntityManager();
        TypedQuery<Category> query = entityManager.createQuery("from Category c where c.categoryType.id=:id", Category.class);
        query.setParameter("id", id);
        List<Category> categories = query.getResultList();
        entityManager.close();
        return categories;
    }
}
