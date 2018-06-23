package com.agh.edu.api.service;

import com.agh.edu.api.dto.CategoryDto;
import com.agh.edu.api.dto.CategoryTypeDto;
import com.agh.edu.api.dto.ElementDto;
import com.agh.edu.api.dto.ElementTypeDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    void addNewCategory(CategoryDto categoryDto);

    void addNewElement(ElementDto elementDto);

    void addNewElementType(ElementTypeDto elementTypeDto);

    void addNewCategoryType(CategoryTypeDto categoryTypeDto);

    void editCategory(CategoryDto categoryDto);

    void editElement(ElementDto elementDto);

    void deleteCategory(CategoryDto categoryDto);

    void deleteElement(ElementDto elementDto);

    CategoryDto getCategoryById(Integer id);

    List<CategoryTypeDto> getAllCategoryTypes();

    List<CategoryTypeDto> getCategoryTypesByUserId();

    CategoryTypeDto getCategoryTypeById(Integer id);

    List<CategoryDto> getCategoriesByCategoryType(CategoryTypeDto categoryTypeDto);

    List<CategoryDto> getCategoriesByCategoryTypeAndUserId(CategoryTypeDto categoryTypeDto);

    ElementDto initElementDtoByCategory(CategoryDto categoryDto);

    ElementDto initElementDtoByCategoryType(CategoryTypeDto categoryTypeDto);

    List<ElementDto> getElementsByCategory(CategoryDto categoryDto);

    List<ElementDto> getBestElementFromEachCategory();

    ElementDto getElementById(Integer id);
}
