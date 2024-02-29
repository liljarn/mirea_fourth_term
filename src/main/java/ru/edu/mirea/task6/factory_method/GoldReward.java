package ru.edu.mirea.task6.factory_method;

public class GoldReward implements GameItem {
    @Override
    public void open() {
        System.out.println("GoldReward opened");
    }
}
