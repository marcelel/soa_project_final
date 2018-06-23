package com.agh.edu.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class CategoryDto implements Serializable {

    private Integer id;
    private CategoryTypeDto categoryTypeDto;
    private Integer value;
    private List<ElementDto> elements;
    private Integer userId;

    @Override
    public String toString() {
        return String.format("%s: %d", categoryTypeDto != null ? categoryTypeDto.getParameter() : StringUtils.EMPTY, value);
    }
}
