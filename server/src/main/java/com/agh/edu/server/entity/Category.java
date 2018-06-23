package com.agh.edu.server.entity;

import com.agh.edu.server.core.ModelObject;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "CATEGORY", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "id"})
})
public class Category implements ModelObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "category_type_id", referencedColumnName = "id")
    @ManyToOne
    private CategoryType categoryType;

    @Column(name = "value")
    private Integer value;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Element> elements;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;

    public Category() {
    }

    public Category(CategoryType categoryType, Integer value) {
        this.categoryType = categoryType;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", value=" + value +
                ", elements=" + elements +
                ", user=" + user +
                '}';
    }
}
