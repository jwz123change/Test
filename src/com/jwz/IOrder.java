package com.jwz;

import java.math.BigDecimal;
import java.util.List;

public interface IOrder {

    BigDecimal pay(List<OrderItem> orders,FullReductionStrategy fullReductionStrategy);


}