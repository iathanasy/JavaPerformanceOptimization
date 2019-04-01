package com.cd.chapter02.builder;

import java.util.ArrayList;

/**
 * @description: 各种设施都有了 我们按照一定的顺序j建造一个奔驰车
 * @author: Mr.Wang
 * @create: 2019-04-01 22:13
 **/
public class BenzBuilder extends CarBuilder{
    private BenzModel benz = new BenzModel();
    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.benz;
    }
}
