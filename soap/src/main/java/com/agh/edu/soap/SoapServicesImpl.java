package com.agh.edu.soap;

import com.agh.edu.api.dto.CategoryTypeDto;
import com.agh.edu.api.dto.ElementDto;
import com.agh.edu.api.dto.ElementTypeDto;
import com.agh.edu.api.service.CategoryService;
import javax.ejb.EJB;
import javax.jws.WebService;

@WebService
public class SoapServicesImpl implements SoapServices {
    
    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!com.agh.edu.api.service.CategoryService")
    private CategoryService categoryService;

    @Override
    public void addNewCategoryType(CategoryTypeDto categoryTypeDto) {
        categoryService.addNewCategoryType(categoryTypeDto);
    }

    @Override
    public void addNewElementType(ElementTypeDto elementTypeDto) {
        categoryService.addNewElementType(elementTypeDto);
    }

    @Override
    public void editElement(ElementDto elementDto) {
        categoryService.editElement(elementDto);
    }

    @Override
    public ElementDto getElementDtoById(Integer id) {
        return categoryService.getElementById(id);
    }
}
