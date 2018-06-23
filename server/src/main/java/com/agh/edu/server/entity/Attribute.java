package com.agh.edu.server.entity;

import lombok.Data;

import javax.persistence.*;
import com.agh.edu.server.core.ModelObject;

import java.util.List;

@Data
@Entity
public class Attribute implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "element_id")
    private Integer elementId;

    @JoinColumn(name = "element_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private Element element;

    public Attribute() {
    }

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
