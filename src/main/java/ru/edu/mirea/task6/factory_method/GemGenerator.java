package ru.edu.mirea.task6.factory_method;

public class GemGenerator extends ItemGenerator{
    @Override
    public GameItem createItem() {
        return new GemReward();
    }
}
