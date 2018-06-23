package com.agh.edu.server.dao;

import com.agh.edu.server.core.AbstractDao;
import com.agh.edu.server.core.EMF;
import com.agh.edu.server.entity.ElementType;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.Optional;

@ApplicationScoped
public class ElementTypeDao extends AbstractDao<ElementType> {

    public ElementTypeDao() {
        setClazz(ElementType.class);
    }

    public Optional<ElementType> getElementTypeByCategoryTypeId(Integer id) {
        entityManager = EMF.createEntityManager();
        TypedQuery<ElementType> query = entityManager.createNamedQuery("ElementType.findByCategoryType", ElementType.class);
        query.setParameter("categoryTypeId", id);
        Optional<ElementType> elementType;
        try {
            elementType = Optional.ofNullable(query.getSingleResult());
        } catch (Exception ex) {
            ex.printStackTrace();
            return Optional.empty();
        } finally {
            entityManager.close();
        }
        return elementType;
    }
}
