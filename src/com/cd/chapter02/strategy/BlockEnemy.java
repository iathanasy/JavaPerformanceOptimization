package com.cd.chapter02.strategy;

/**
 * @description: 具体策略操作二
 * @author: Mr.Wang
 * @create: 2019-03-26 22:42
 **/
public class BlockEnemy implements IStrategy {
    @Override
    public void operate() {
        System.out.println("操作二...");
    }
}
