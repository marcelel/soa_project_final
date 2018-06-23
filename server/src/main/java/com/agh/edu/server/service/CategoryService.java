package com.agh.edu.server.service;

import com.agh.edu.api.dto.CategoryDto;
import com.agh.edu.api.dto.CategoryTypeDto;
import com.agh.edu.api.dto.ElementDto;
import com.agh.edu.api.dto.ElementTypeDto;
import com.agh.edu.server.adapter.CategoryDtoAdapter;
import com.agh.edu.server.adapter.CategoryTypeDtoAdapter;
import com.agh.edu.server.adapter.ElementDtoAdapter;
import com.agh.edu.server.dao.*;
import com.agh.edu.server.entity.*;
import com.agh.edu.server.interceptor.NewElementAudited;
import com.agh.edu.server.util.UserUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Remote(com.agh.edu.api.service.CategoryService.class)
@Stateless
public class CategoryService implements com.agh.edu.api.service.CategoryService {

    @Inject
    private CategoryDao categoryDao;

    @Inject
    private CategoryTypeDao categoryTypeDao;

    @Inject
    private ElementDao elementDao;

    @Inject
    private AttributeDao attributeDao;

    @Inject
    private ElementTypeDao elementTypeDao;

    @Inject
    private UserUtils userUtils;

    @Inject
    private JMSService jmsService;

    private CategoryTypeDtoAdapter categoryTypeDtoAdapter;
    private CategoryDtoAdapter categoryDtoAdapter;
    private ElementDtoAdapter elementDtoAdapter;

    public CategoryService() {
        categoryTypeDtoAdapter = new CategoryTypeDtoAdapter();
        categoryDtoAdapter = new CategoryDtoAdapter();
        elementDtoAdapter = new ElementDtoAdapter();
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryDao.getAllEntities().stream()
                .map(x -> categoryDtoAdapter.createCategoryDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public void addNewElementType(ElementTypeDto elementTypeDto) {
        ElementType elementType = new ElementType();
        elementType.setName(elementTypeDto.getName());
        elementType.setAttributeAmountLabel(elementTypeDto.getAttributeAmountLabel());
        elementType.setAttributeNameLabel(elementTypeDto.getAttributeNameLabel());
        CategoryType categoryType = categoryTypeDao.getById(elementTypeDto.getCategoryTypeId());
        elementType.setCategoryType(categoryType);
        elementTypeDao.save(elementType);
    }

    @Override
    public void addNewCategoryType(CategoryTypeDto categoryTypeDto) {
        CategoryType categoryType = new CategoryType();
        categoryType.setName(categoryTypeDto.getName());
        categoryType.setParameter(categoryTypeDto.getParameter());
        categoryTypeDao.save(categoryType);
    }

    @Override
    public void addNewCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setValue(categoryDto.getValue());
        CategoryType categoryType = categoryTypeDao.getById(categoryDto.getCategoryTypeDto().getId());
        category.setCategoryType(categoryType);
        category.setUser(userUtils.getLoggedUser());
        categoryDao.save(category);
    }

    @Override
    @NewElementAudited
    public void addNewElement(ElementDto elementDto) {
        Element element = new Element();
        Category category = categoryDao.getById(elementDto.getCategoryId());
        Optional<ElementType> elementType = elementTypeDao.getElementTypeByCategoryTypeId(category.getCategoryType().getId());
        element.setCategory(category);
        element.setElementType(elementType.get());
        element.setName(elementDto.getName());
        element.setAttributeAmount(elementDto.getAttributeAmount());
        element.setPower(elementDto.getPower());
        elementDao.save(element);
        Attribute attribute = new Attribute();
        attribute.setName(elementDto.getAttributeNameLabel());
        attribute.setValue(elementDto.getAttributeName());
        attribute.setElementId(element.getId());
        attributeDao.save(attribute);
        sendNotificationToUser(element);
    }

    private void sendNotificationToUser(Element element) {
        ElementType elementTypeByElement = element.getElementType();
        List<Element> elementsByElementType = elementDao.getElementsByElementType(elementTypeByElement);
        List<String> users = elementsByElementType.stream().map(x -> x.getCategory().getUser().getLogin()).distinct().collect(Collectors.toList());
        users.forEach(x -> jmsService.sendMessage("username", x, "New element added"));
    }

    @Override
    public void editCategory(CategoryDto categoryDto) {
        Category category = categoryDao.getById(categoryDto.getId());
        category.setValue(categoryDto.getValue());
        categoryDao.update(category);
    }

    @Override
    public void editElement(ElementDto elementDto) {
        Element element = elementDao.getById(elementDto.getId());
        if (element != null) {
            if (StringUtils.isNotEmpty(elementDto.getName())) {
                element.setName(elementDto.getName());
            }
            if (elementDto.getAttributeAmount() != null) {
                element.setAttributeAmount(elementDto.getAttributeAmount());
            }
            if (StringUtils.isNotEmpty(elementDto.getAttributeName())) {
                element.getAttribute().setValue(elementDto.getAttributeName());
            }
            if (elementDto.getPower() != null) {
                element.setPower(elementDto.getPower());
            }
            elementDao.update(element);
        }
    }

    @Override
    public void deleteCategory(CategoryDto categoryDto) {
        Category category = categoryDao.getById(categoryDto.getId());
        categoryDao.delete(category);
    }

    @Override
    public void deleteElement(ElementDto elementDto) {
        Element element = elementDao.getById(elementDto.getId());
        elementDao.delete(element);
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category = categoryDao.getById(id);
        return category != null ? categoryDtoAdapter.createCategoryDto(category) : null;
    }

    @Override
    public List<CategoryTypeDto> getAllCategoryTypes() {
        List<CategoryType> categoryTypes = categoryTypeDao.getAllEntities();
        return categoryTypes.stream()
                .map(x -> categoryTypeDtoAdapter.createCategoryTypeDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryTypeDto> getCategoryTypesByUserId() {
        User loggedUser = userUtils.getLoggedUser();
        List<CategoryType> categoryTypes;
        if (userUtils.isAdmin(loggedUser)) {
            categoryTypes = categoryTypeDao.getAllEntities();
        } else {
            categoryTypes = categoryTypeDao.getCategoriesTypeByUserId(loggedUser.getId());
        }
        return categoryTypes.stream()
                .map(x -> categoryTypeDtoAdapter.createCategoryTypeDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryTypeDto getCategoryTypeById(Integer id) {
        CategoryType categoryType = categoryTypeDao.getById(id);
        return categoryTypeDtoAdapter.createCategoryTypeDto(categoryType);
    }

    @Override
    public List<CategoryDto> getCategoriesByCategoryType(CategoryTypeDto categoryTypeDto) {
            return categoryDao.getCategoriesByCategoryTypeId(categoryTypeDto.getId()).stream()
                    .map(x -> categoryDtoAdapter.createCategoryDto(x))
                    .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getCategoriesByCategoryTypeAndUserId(CategoryTypeDto categoryTypeDto) {
        User loggedUser = userUtils.getLoggedUser();
        if (userUtils.isAdmin(loggedUser)) {
            return categoryDao.getCategoriesByCategoryTypeId(categoryTypeDto.getId()).stream()
                    .map(x -> categoryDtoAdapter.createCategoryDto(x))
                    .collect(Collectors.toList());
        } else {
            return loggedUser.getCategories().stream()
                    .filter(x -> x.getCategoryType().getId().equals(categoryTypeDto.getId()))
                    .map(x -> categoryDtoAdapter.createCategoryDto(x))
                    .collect(Collectors.toList());
        }
    }

    public ElementDto initElementDtoByCategory(CategoryDto categoryDto) {
        return initElementDtoByCategoryType(categoryDto.getCategoryTypeDto());
    }

    public ElementDto initElementDtoByCategoryType(CategoryTypeDto categoryTypeDto) {
        Optional<ElementType> elementType = elementTypeDao.getElementTypeByCategoryTypeId(categoryTypeDto.getId());
        if (elementType.isPresent()) {
            ElementDto elementDto = new ElementDto();
            elementDto.setElementTypeName(elementType.get().getName());
            elementDto.setAttributeNameLabel(elementType.get().getAttributeNameLabel());
            elementDto.setAttributeAmountLabel(elementType.get().getAttributeAmountLabel());
            return elementDto;
        } else {
            return new ElementDto();
        }
    }

    @Override
    public List<ElementDto> getElementsByCategory(CategoryDto categoryDto) {
        User loggedUser = userUtils.getLoggedUser();
        if (userUtils.isAdmin(loggedUser) || categoryDto.getUserId().equals(loggedUser.getId())) {
            return elementDao.getElementsByCategoryId(categoryDto.getId()).stream()
                    .map(x -> elementDtoAdapter.createElementDto(x, categoryDto))
                    .collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<ElementDto> getBestElementFromEachCategory() {
        return elementDao.getBestElementFromEachCategory().stream()
                .map(x -> elementDtoAdapter.createElementDto(x, categoryDtoAdapter.createCategoryDto(x.getCategory())))
                .collect(Collectors.toList());
    }

    @Override
    public ElementDto getElementById(Integer id) {
        Element element = elementDao.getById(id);
        return element != null ? elementDtoAdapter.createElementDto(element) : null;
    }
}
