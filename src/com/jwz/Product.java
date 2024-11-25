package com.jwz;

import java.math.BigDecimal;

public class Product {
    public Product() {
    }

    private BigDecimal price;
    private  String ProductName;
    private  Float discount = 1.0f;
    public Product(BigDecimal price, String productName, Float discount) {
        this.price = price;
        ProductName = productName;
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

}
