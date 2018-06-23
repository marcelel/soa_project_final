package com.agh.edu.client.converter;

import com.agh.edu.api.dto.CategoryDto;
import com.agh.edu.api.dto.ElementDto;
import com.agh.edu.api.service.CategoryService;
import com.agh.edu.client.controller.CategoryController;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@FacesConverter(value = "elementConverter")
public class ElementConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        ValueExpression vex = context.getApplication(). getExpressionFactory()
                .createValueExpression(context.getELContext(), "#{categoryController}", CategoryController.class);
        CategoryController categoryController = (CategoryController) vex.getValue(context.getELContext());
        return categoryController.getElements().stream().filter(x -> x.getId().equals(Integer.valueOf(value))).findFirst().get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return StringUtils.EMPTY;
        }
        ElementDto elementDto= (ElementDto) value;
        return String.valueOf(elementDto.getId());
    }
}