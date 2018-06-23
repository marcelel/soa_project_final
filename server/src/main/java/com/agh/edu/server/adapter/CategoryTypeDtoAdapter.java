package com.agh.edu.server.adapter;

import com.agh.edu.api.dto.CategoryTypeDto;
import com.agh.edu.server.entity.CategoryType;

import java.util.stream.Collectors;

public class CategoryTypeDtoAdapter {

    public CategoryTypeDto createCategoryTypeDto(CategoryType categoryType) {
        CategoryDtoAdapter categoryDtoAdapter = new CategoryDtoAdapter();

        CategoryTypeDto categoryTypeDto = new CategoryTypeDto();
        categoryTypeDto.setId(categoryType.getId());
        categoryTypeDto.setName(categoryType.getName());
        categoryTypeDto.setParameter(categoryType.getParameter());
        categoryTypeDto.setCategories(categoryType.getCategories().stream()
                .map(x -> categoryDtoAdapter.createCategoryDto(x, categoryTypeDto))
                .collect(Collectors.toList())
        );
        return categoryTypeDto;
    }
}
