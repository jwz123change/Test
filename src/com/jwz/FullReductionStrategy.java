package com.jwz;

public class FullReductionStrategy {
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
