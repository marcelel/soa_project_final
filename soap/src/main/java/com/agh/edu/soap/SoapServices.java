package com.agh.edu.soap;

import com.agh.edu.api.dto.CategoryTypeDto;
import com.agh.edu.api.dto.ElementDto;
import com.agh.edu.api.dto.ElementTypeDto;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SoapServices {

    @WebMethod
    void addNewCategoryType(CategoryTypeDto categoryTypeDto);

    @WebMethod
    void addNewElementType(ElementTypeDto elementTypeDto);

    @WebMethod
    void editElement(ElementDto elementDto);

    @WebMethod
    ElementDto getElementDtoById(Integer id);
}

