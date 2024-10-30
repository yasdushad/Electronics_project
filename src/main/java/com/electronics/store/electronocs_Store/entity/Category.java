package com.electronics.store.electronocs_Store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @Column(name="id")
    private String categoryId;
    @Column(name="category_title",length=68,nullable = false)
    private String title;
    @Column(name="category_desc",length=58,nullable = false)
    private String description;
    @Column(name="category_coverImage",length=68)
    private String coverImage;

}
