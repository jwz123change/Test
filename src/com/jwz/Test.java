package com.jwz;

import org.junit.Before;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Test {


    List<OrderItem> orders = new ArrayList<>();
    Product apple = ProductFactory.createProduct(FruitEnum.APPLE);
    Product strawberry = ProductFactory.createProduct(FruitEnum.APPLE);

    @Before
    public void beforeTest() {
        apple.setPrice(new BigDecimal("8"));
        strawberry.setPrice(new BigDecimal("13"));
        OrderItem p1 = new OrderItem(apple, 1);
        OrderItem p2 = new OrderItem(strawberry, 4);
        orders.add(p1);
        orders.add(p2);

    }

    /**
     * case1
     */
    @org.junit.Test
    public void test() {
        IOrder order = new Order();
        BigDecimal result = order.pay(orders, null);
        assertEquals(new BigDecimal("60.00"), result);

    }

    /**
     * case2
     */
    @org.junit.Test
    public void test1() {
        IOrder order = new Order();
        Product mango = ProductFactory.createProduct(FruitEnum.MANGO);
        mango.setPrice(new BigDecimal("20"));
        orders.add(new OrderItem(mango, 15));
        BigDecimal result = order.pay(orders, null);
        assertEquals(new BigDecimal("360.00"), result);


    }

    /**
     * case3
     */
    @org.junit.Test
    public void test2() {
        IOrder order = new Order();
        Product mango = ProductFactory.createProduct(FruitEnum.MANGO);
        mango.setPrice(new BigDecimal("20"));
        mango.setDiscount(0.8f);
        orders.add(new OrderItem(mango, 15));
        BigDecimal result = order.pay(orders, null);
        assertEquals(new BigDecimal("300.00"), result);

    }

    /**
     * case4,边界刚好100整数
     */
    @org.junit.Test
    public void test3() {
        IOrder order = new Order();
        Product mango = ProductFactory.createProduct(FruitEnum.MANGO);
        mango.setPrice(new BigDecimal("20"));
        mango.setDiscount(0.8f);
        orders.add(new OrderItem(mango, 15));
        FullReductionStrategy fullReductionStrategy = new FullReductionStrategy(100, 10);
        BigDecimal result = order.pay(orders, fullReductionStrategy);
        assertEquals(new BigDecimal("270.00"), result);

    }

    /**
     * case4
     */
    @org.junit.Test
    public void test4() {
        IOrder order = new Order();
        Product mango = ProductFactory.createProduct(FruitEnum.MANGO);
        mango.setPrice(new BigDecimal("20"));
        mango.setDiscount(0.8f);
        orders.add(new OrderItem(mango, 16));
        FullReductionStrategy fullReductionStrategy = new FullReductionStrategy(100, 10);
        BigDecimal result = order.pay(orders, fullReductionStrategy);
        assertEquals(new BigDecimal("286.00"), result);

    }


}
