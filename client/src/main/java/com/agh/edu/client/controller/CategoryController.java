package com.agh.edu.client.controller;

import com.agh.edu.api.dto.CategoryDto;
import com.agh.edu.api.dto.CategoryTypeDto;
import com.agh.edu.api.dto.ElementDto;
import com.agh.edu.api.service.CategoryService;
import com.agh.edu.client.event.ElementEvent;
import com.agh.edu.client.jms.ReceiverController;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ConversationScoped
public class CategoryController implements Serializable {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!com.agh.edu.api.service.CategoryService")
    private CategoryService categoryService;

    @Inject
    private Conversation conversation;

    @Inject
    private Event<ElementEvent> elementEvent;

    @Getter
    private List<CategoryDto> categories;

    @Getter
    @Setter
    private CategoryDto category;

    @Getter
    @Setter
    private CategoryDto elementCategory;

    @Getter
    private List<CategoryTypeDto> categoryTypes;

    @Getter
    @Setter
    private CategoryTypeDto categoryType;

    @Getter
    @Setter
    private CategoryTypeDto elementCategoryType;

    @Getter
    @Setter
    private ElementDto element;

    @Getter
    @Setter
    private List<ElementDto> elements;

    @Getter
    @Setter
    private List<ElementDto> bestElementFromEachCategoryList;

    @Getter private String dialogMode;

    @Inject
    private ReceiverController receiverController;

    @PostConstruct
    public void init() {
        dialogMode = getRequestParameter("dialogMode");
        if (isEditDialogMode()) {
            categoryTypes = categoryService.getCategoryTypesByUserId();
            if (categoryTypes.isEmpty()) {
                return;
            }
            categoryType = categoryTypes.get(0);
            categories = categoryService.getCategoriesByCategoryTypeAndUserId(categoryType);
            category = new CategoryDto();
            category.setCategoryTypeDto(categoryType);
            elementCategoryType = categoryTypes.get(0);
            elementCategory = categories.get(0);
            elementCategory.setCategoryTypeDto(elementCategoryType);
            elements = elementCategory.getElements();
            element = categoryService.initElementDtoByCategoryType(elementCategoryType);
            element.setCategory(elementCategory);
        } else {
            categoryTypes = categoryService.getAllCategoryTypes();
            categoryType = categoryTypes.get(0);
            categories = categoryService.getCategoriesByCategoryType(categoryType);
            category = new CategoryDto();
            category.setCategoryTypeDto(categoryType);
            elementCategoryType = categoryTypes.get(0);
            elementCategory = categories.get(0);
            elementCategory.setCategoryTypeDto(elementCategoryType);
            elements = elementCategory.getElements();
            element = categoryService.initElementDtoByCategoryType(elementCategoryType);
            element.setCategory(elementCategory);
        }
        bestElementFromEachCategoryList = categoryService.getBestElementFromEachCategory();
        receiverController.start();
    }

    public void initConversation(){
        if (!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void createCategory() {
        categoryService.addNewCategory(category);
        category = new CategoryDto();
        category.setCategoryTypeDto(categoryType);
    }

    public void createElement() {
        categoryService.addNewElement(element);
        element = categoryService.initElementDtoByCategoryType(elementCategoryType);
        element.setCategory(elementCategory);
        fireElementEvent();
    }

    public void editCategory() {
        categoryService.editCategory(category);
        category = new CategoryDto();
        category.setCategoryTypeDto(categoryType);
    }

    public void editElement() {
        categoryService.editElement(element);
        elements = categoryService.getElementsByCategory(elementCategory);
        element = categoryService.initElementDtoByCategoryType(elementCategoryType);
        element.setCategory(elementCategory);
        fireElementEvent();
    }

    public void deleteCategory(CategoryDto category) {
        categoryService.deleteCategory(category);
        init();
    }

    public void deleteElement(ElementDto element) {
        categoryService.deleteElement(element);
        init();
        fireElementEvent();
    }

    public List<String> getCategoryTypeNames() {
        return categoryTypes.stream().map(CategoryTypeDto::getName).collect(Collectors.toList());
    }

    public void categoryTypeChangeListener() {
        categories = categoryService.getCategoriesByCategoryType(categoryType);
    }

    public void editCategoryTypeChangeListener() {
        category.setCategoryTypeDto(categoryType);
        categories = categoryService.getCategoriesByCategoryType(categoryType);
    }

    public void addElementCategoryTypeChangeListener() {
        categories = categoryService.getCategoriesByCategoryType(elementCategoryType);
        elementCategory = categories.isEmpty() ? new CategoryDto() : categories.get(0);
        elementCategory.setCategoryTypeDto(elementCategoryType);
        elements = elementCategory.getElements();
        element = categoryService.initElementDtoByCategoryType(elementCategoryType);
        element.setCategory(elementCategory);
    }

    public void addElementCategoryChangeListener() {
        elementCategory.setCategoryTypeDto(elementCategoryType);
        elements = elementCategory.getElements();
        element = categoryService.initElementDtoByCategoryType(elementCategoryType);
        element.setCategory(elementCategory);
    }

    public boolean isEditDialogMode() {
        return "edit".equals(dialogMode);
    }

    public boolean isCreateDialogMode() {
        return "create".equals(dialogMode);
    }

    private void updateBestElementFromEachCategoryList(@Observes ElementEvent elementEvent) {
        bestElementFromEachCategoryList = categoryService.getBestElementFromEachCategory();
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/notify", new FacesMessage("Best elements updated"));
    }

    private String getRequestParameter(String parameterName) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return requestParameterMap.get(parameterName);
    }

    private void fireElementEvent() {
        ElementEvent event = new ElementEvent();
        elementEvent.fire(event);
    }
}
