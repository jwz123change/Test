package com.jwz;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Product apple=new Product(new BigDecimal("8"),"apple",1.00f);
        Product strawberry=new Product(new BigDecimal("13"),"strawberry",1.00f);
        OrderItem p1=new OrderItem(apple,1);
        OrderItem p2=new OrderItem(strawberry,4);
        List<OrderItem> orders= Arrays.asList(p1,p2);
        //8*1+13*4=60
        System.out.println("A消费：apple 1斤，strawberry 4斤。合计"+ getPrice(orders, null)+"元");
        Product mango=new Product(new BigDecimal("20"),"mango",1.00f);
        OrderItem p3=new OrderItem(mango,15);
        //8+13*4+20*15=60+300=360
        List<OrderItem> orders1= Arrays.asList(p1,p2,p3);
        System.out.println("B消费：apple 1斤，strawberry 4斤，mango 15斤。合计"+ getPrice(orders1, null)+"元");
        mango.setDiscount(0.8f);

        //8+13*4+20*15*0.8=60+240=300
        System.out.println("C消费：apple 1斤，strawberry 4斤，mango 15斤。合计"+ getPrice(orders1, null)+"元");
        FullReductionStrategy fullReductionStrategy=new FullReductionStrategy(100,10);

        // 刚好100倍数 300-30=270
        System.out.println("D消费：apple 1斤，strawberry 4斤，mango 15斤。合计"+ getPrice(orders1,fullReductionStrategy)+"元");
        // 不是100倍数 316-30=286
        OrderItem p4=new OrderItem(mango,16);
        List<OrderItem> orders2= Arrays.asList(p1,p2,p4);
        System.out.println("D消费：apple 1斤，strawberry 4斤，mango 16斤。合计"+ getPrice(orders2,fullReductionStrategy)+"元");



    }
    public static BigDecimal  getPrice(List<OrderItem> orders,FullReductionStrategy fullReductionStrategy){
        BigDecimal total=BigDecimal.ZERO;
        //商品折扣
        total=  orders.stream().
                map(item-> item.getProduct().getPrice().multiply(new BigDecimal(item.getNum())).multiply(
                        new BigDecimal(item.getProduct().getDiscount()))).
                reduce(BigDecimal.ZERO,BigDecimal::add);
        if(fullReductionStrategy!=null){
            //满减
            int i = total.divide(new BigDecimal(fullReductionStrategy.getFullMoney())).intValue();
            total=total.subtract(BigDecimal.valueOf(i*fullReductionStrategy.getReduction()));
        }
        return  total.setScale(2, RoundingMode.HALF_UP);


    }

    public static class FullReductionStrategy {
        private int  fullMoney;
        private int reduction;



        public  FullReductionStrategy(int fullMoney, int reduction) {
            this.fullMoney = fullMoney;
            this.reduction = reduction;
        }

        public int getFullMoney() {
            return fullMoney;
        }

        public void setFullMoney(int fullMoney) {
            this.fullMoney = fullMoney;
        }

        public int getReduction() {
            return reduction;
        }

        public void setReduction(int reduction) {
            this.reduction = reduction;
        }
    }


    public static class Product {
        private BigDecimal price;
        private  String ProductName;
        private  Float discount;

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

    public static class OrderItem {
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
}
