package com.jwz;

public class OrderItem {
    private Product product;
    private int num;

    public OrderItem(Product product, int num) {
        this.product = product;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

