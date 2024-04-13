package com.example.stock.dto;

import com.example.stock.domain.Category;
import com.example.stock.domain.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "O campo nome é requerido")
    private String name;
    @NotNull(message = "O campo preço é requerido")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal price;
    private String description;
    @NotNull(message = "O campo preço é requerido")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal amount;

    private Category category;

    public ProductDTO(Product obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.price = obj.getPrice();
        this.description = obj.getDescription();
        this.amount = obj.getAmount();
        this.category = obj.getCategory();
    }

    public ProductDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
