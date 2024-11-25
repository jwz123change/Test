package com.jwz;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Order implements IOrder{


    @Override
    public BigDecimal pay(List<OrderItem> orders, FullReductionStrategy fullReductionStrategy) {
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
}
