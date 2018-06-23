package com.agh.edu.server.entity;

import lombok.Data;

import javax.persistence.*;
import com.agh.edu.server.core.ModelObject;

@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "Element.getElementsByCategoryId",
                query = "SELECT e FROM Element e WHERE e.category.id=:categoryId"),
        @NamedQuery(name = "Element.getMaxPowerFromCategory",
                query = "select max(e.power) FROM Element e WHERE e.category.id=:categoryId")
})
public class Element implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private Category category;

    @JoinColumn(name = "element_type_id", referencedColumnName = "id")
    @ManyToOne
    private ElementType elementType;

    @Column(name = "name")
    private String name;

    @Column(name = "attribute_amount")
    private Integer attributeAmount;

    @OneToOne(mappedBy = "element", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Attribute attribute;

    @Column(name = "power")
    private Integer power;

    public Element() {
    }

    public Element(Category category, ElementType elementType, String name, Integer attributeAmount, Attribute attribute, Integer power) {
        this.category = category;
        this.elementType = elementType;
        this.name = name;
        this.attributeAmount = attributeAmount;
        this.attribute = attribute;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Element{" +
                "name='" + name + '\'' +
                '}';
    }
}
