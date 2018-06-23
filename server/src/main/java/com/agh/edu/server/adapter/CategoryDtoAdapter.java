package com.agh.edu.server.adapter;

import com.agh.edu.api.dto.CategoryDto;
import com.agh.edu.api.dto.CategoryTypeDto;
import com.agh.edu.server.entity.Category;

import java.util.stream.Collectors;

public class CategoryDtoAdapter {

    public CategoryDto createCategoryDto(Category category) {
        ElementDtoAdapter elementDtoAdapter = new ElementDtoAdapter();
        CategoryTypeDtoAdapter categoryTypeDtoAdapter = new CategoryTypeDtoAdapter();

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setValue(category.getValue());
        categoryDto.setCategoryTypeDto(categoryTypeDtoAdapter.createCategoryTypeDto(category.getCategoryType()));
        categoryDto.setElements(category.getElements().stream()
                .map(x -> elementDtoAdapter.createElementDto(x, categoryDto))
                .collect(Collectors.toList()));
        categoryDto.setUserId(category.getUser().getId());
        return categoryDto;
    }

    public CategoryDto createCategoryDto(Category category, CategoryTypeDto categoryTypeDto) {
        ElementDtoAdapter elementDtoAdapter = new ElementDtoAdapter();

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setValue(category.getValue());
        categoryDto.setCategoryTypeDto(categoryTypeDto);
        categoryDto.setElements(category.getElements().stream()
                .map(x -> elementDtoAdapter.createElementDto(x, categoryDto))
                .collect(Collectors.toList()));
        categoryDto.setUserId(category.getUser().getId());
        return categoryDto;
    }
}
