package com.agh.edu.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Data
public class ElementTypeDto implements Serializable {

    private Integer id;
    private String name;
    private String attributeAmountLabel;
    private String attributeNameLabel;
    private Integer categoryTypeId;
    private List<ElementDto> elements;
}
