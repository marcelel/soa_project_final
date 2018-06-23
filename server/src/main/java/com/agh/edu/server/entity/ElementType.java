package com.agh.edu.server.entity;

import lombok.Data;

import javax.persistence.*;
import com.agh.edu.server.core.ModelObject;
import java.util.List;

@Data
@Entity
@Table(name = "ELEMENT_TYPE")
@NamedQueries({
        @NamedQuery(name="ElementType.findByCategoryType",
                query="SELECT et FROM ElementType et WHERE et.categoryType.id=:categoryTypeId"),
})
public class ElementType implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "attribute_amount_label")
    private String attributeAmountLabel;

    @Column(name = "attribute_name_label")
    private String attributeNameLabel;

    @OneToMany(mappedBy = "elementType", cascade = CascadeType.ALL)
    private List<Element> elements;

    @JoinColumn(name = "category_type_id", referencedColumnName = "id")
    @OneToOne
    private CategoryType categoryType;

    public ElementType() {
    }

    public ElementType(String name, String attributeAmountLabel, String attributeNameLabel) {
        this.name = name;
        this.attributeAmountLabel = attributeAmountLabel;
        this.attributeNameLabel = attributeNameLabel;
    }
}
