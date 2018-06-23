package com.agh.edu.server.entity;

import com.agh.edu.server.core.ModelObject;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "category_type")
public class CategoryType implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "parameter")
    private String parameter;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryType", fetch = FetchType.EAGER )
    private List<Category> categories;

    @OneToOne(mappedBy = "categoryType", cascade = CascadeType.ALL)
    private ElementType elementType;

    public CategoryType() {
    }

    public CategoryType(String name, String parameter) {
        this.name = name;
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "CategoryType{" +
                "name='" + name + '\'' +
                '}';
    }
}
