package com.jwz;

public enum FruitEnum {
    APPLE("apple"),
    STRAWBERRY("strawberry"),
    MANGO("mango");


    private final String name;

    FruitEnum(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
