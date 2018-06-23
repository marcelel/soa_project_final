package com.agh.edu.client.converter;

import com.agh.edu.api.dto.CategoryDto;
import com.agh.edu.api.dto.UserDto;
import com.agh.edu.api.service.CategoryService;
import com.agh.edu.client.controller.CategoryController;
import com.agh.edu.client.controller.UserController;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        ValueExpression vex = context.getApplication(). getExpressionFactory()
                .createValueExpression(context.getELContext(), "#{userController}", UserController.class);
        UserController userController = (UserController) vex.getValue(context.getELContext());
        return userController.getUsers().stream().filter(x -> x.getId().equals(Integer.valueOf(value))).findFirst().get();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return StringUtils.EMPTY;
        }
        UserDto userDto = (UserDto) value;
        return String.valueOf(userDto.getId());
    }
}
