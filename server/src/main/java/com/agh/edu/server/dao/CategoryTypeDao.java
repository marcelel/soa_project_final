package com.agh.edu.server.dao;

import com.agh.edu.api.dto.CategoryDto;
import com.agh.edu.api.dto.CategoryTypeDto;
import com.agh.edu.server.core.AbstractDao;
import com.agh.edu.server.core.EMF;
import com.agh.edu.server.entity.CategoryType;
import com.agh.edu.server.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class CategoryTypeDao extends AbstractDao<CategoryType> {

    public CategoryTypeDao() {
        setClazz(CategoryType.class);
    }

    public List<CategoryType> getCategoriesTypeByUserId(Integer userId) {
        entityManager = EMF.createEntityManager();
        TypedQuery<CategoryType> query = entityManager.createQuery("from CategoryType ct where ct.id in " +
                "(select c.categoryType.id from Category c where c.user.id=:userId)", CategoryType.class);
        query.setParameter("userId", userId);
        List<CategoryType> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }
}
