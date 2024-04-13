package com.example.stock.dto;

import com.example.stock.domain.Category;
import com.example.stock.domain.Product;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable {

    private Integer categoryId;

    @NotEmpty(message = "O campo nome é requerido!")
    private String name;

    private List<Product> products;

    public CategoryDTO(Category obj) {
        this.categoryId = obj.getCategoryId();
        this.name = obj.getName();
        this.products = obj.getProducts();
    }

    public CategoryDTO() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
