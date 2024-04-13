package com.example.stock.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleDTO implements Serializable {
    private Integer productId;
    private BigDecimal quantitySold;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(BigDecimal quantitySold) {
        this.quantitySold = quantitySold;
    }
}
