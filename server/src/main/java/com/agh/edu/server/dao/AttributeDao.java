package com.agh.edu.server.dao;

import com.agh.edu.server.core.AbstractDao;
import com.agh.edu.server.entity.Attribute;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AttributeDao extends AbstractDao<Attribute> {

    public AttributeDao() {
        setClazz(Attribute.class);
    }
}
