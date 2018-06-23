package com.agh.edu.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ElementDto implements Serializable {

    private Integer id;
    private String elementTypeName;
    private Integer categoryId;
    @JsonIgnore
    @XmlTransient
    private CategoryDto category;
    private String name;
    private String attributeAmountLabel;
    private Integer attributeAmount;
    private String attributeNameLabel;
    private String attributeName;
    private Integer power;

    public void setCategory(CategoryDto category) {
        this.category = category;
        this.categoryId = category != null ? category.getId() : null;
    }

    @Override
    public String toString() {
        return elementTypeName + " " + name + ", " +
                attributeAmountLabel + ": " + attributeAmount + ", " +
                attributeNameLabel + ": " + attributeName +
                ", moc=" + power;
    }
}
