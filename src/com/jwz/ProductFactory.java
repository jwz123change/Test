package com.jwz;

public class ProductFactory {
    public static Product createProduct(FruitEnum fruitEnum)
    {
        switch (fruitEnum)
        {
            case APPLE:
                return new Apple();

            case MANGO:
                return new Mango();
            case STRAWBERRY:
                return new Strawberry();

            default:
                return null;
        }
    }


}
