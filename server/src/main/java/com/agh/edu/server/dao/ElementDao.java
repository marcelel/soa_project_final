package com.agh.edu.server.dao;

import com.agh.edu.api.dto.CategoryDto;
import com.agh.edu.api.dto.ElementDto;
import com.agh.edu.server.core.AbstractDao;
import com.agh.edu.server.core.EMF;
import com.agh.edu.server.entity.Category;
import com.agh.edu.server.entity.Element;
import com.agh.edu.server.entity.ElementType;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ElementDao extends AbstractDao<Element> {

    public ElementDao() {
        setClazz(Element.class);
    }

    public List<Element> getElementsByCategoryId(Integer id) {
        entityManager = EMF.createEntityManager();
        TypedQuery<Element> query = entityManager.createNamedQuery("Element.getElementsByCategoryId", Element.class);
        query.setParameter("categoryId", id);
        return query.getResultList();
    }

    public List<Element> getElementsByElementType(ElementType elementType) {
        entityManager = EMF.createEntityManager();
        TypedQuery<Element> query = entityManager.createQuery("from Element e where e.elementType=:elementType", Element.class);
        query.setParameter("elementType", elementType);
        return query.getResultList();
    }

    public Integer getMaxPowerFromCategory(Integer categoryId) {
        entityManager = EMF.createEntityManager();
        TypedQuery<Integer> query = entityManager.createNamedQuery("Element.getMaxPowerFromCategory", Integer.class);
        query.setParameter("categoryId", categoryId);
        return query.getSingleResult();
    }

    public List<Element> getBestElementFromEachCategory() {
        entityManager = EMF.createEntityManager();
        String query = "select * from element e where e.power = (SELECT max(b.power) from element b where e.category_id=b.category_id group by b.category_id)";
        Query nativeQuery = entityManager.createNativeQuery(query, Element.class);
        return nativeQuery.getResultList();
    }
}
