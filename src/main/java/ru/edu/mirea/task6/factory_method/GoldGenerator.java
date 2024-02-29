package ru.edu.mirea.task6.factory_method;

public class GoldGenerator extends ItemGenerator{
    @Override
    public GameItem createItem() {
        return new GoldReward();
    }
}
