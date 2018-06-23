package com.agh.edu.rest.translator;

import com.agh.edu.api.dto.CategoryDto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Translator {

    public void translate(CategoryDto categoryDto) {
        categoryDto.getElements().forEach(x -> x.setAttributeName("Translated version - " + x.getAttributeName()));
    }
}
