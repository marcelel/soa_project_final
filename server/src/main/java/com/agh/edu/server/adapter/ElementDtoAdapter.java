package com.agh.edu.server.adapter;

import com.agh.edu.api.dto.CategoryDto;
import com.agh.edu.api.dto.ElementDto;
import com.agh.edu.server.entity.Element;

public class ElementDtoAdapter {

    public ElementDto createElementDto(Element element, CategoryDto categoryDto) {
        ElementDto elementDto = new ElementDto();
        elementDto.setId(element.getId());
        elementDto.setElementTypeName(element.getElementType().getName());
        elementDto.setCategory(categoryDto);
        elementDto.setCategoryId(categoryDto != null ? categoryDto.getId() : null);
        elementDto.setName(element.getName());
        elementDto.setAttributeAmountLabel(element.getElementType().getAttributeAmountLabel());
        elementDto.setAttributeAmount(element.getAttributeAmount());
        elementDto.setAttributeNameLabel(element.getElementType().getAttributeNameLabel());
        elementDto.setAttributeName(element.getAttribute().getValue());
        elementDto.setPower(element.getPower());
        return elementDto;
    }

    public ElementDto createElementDto(Element element) {
        CategoryDtoAdapter categoryDtoAdapter = new CategoryDtoAdapter();
        ElementDto elementDto = new ElementDto();
        elementDto.setId(element.getId());
        elementDto.setElementTypeName(element.getElementType().getName());
        CategoryDto categoryDto = categoryDtoAdapter.createCategoryDto(element.getCategory());
        elementDto.setCategory(categoryDto);
        elementDto.setCategoryId(categoryDto != null ? categoryDto.getId() : null);
        elementDto.setName(element.getName());
        elementDto.setAttributeAmountLabel(element.getElementType().getAttributeAmountLabel());
        elementDto.setAttributeAmount(element.getAttributeAmount());
        elementDto.setAttributeNameLabel(element.getElementType().getAttributeNameLabel());
        elementDto.setAttributeName(element.getAttribute().getValue());
        elementDto.setPower(element.getPower());
        return elementDto;
    }
}
