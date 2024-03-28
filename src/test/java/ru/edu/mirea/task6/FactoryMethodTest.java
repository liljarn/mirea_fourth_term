package ru.edu.mirea.task6;

import org.junit.jupiter.api.Test;
import ru.edu.mirea.task6.factory_method.GemGenerator;
import ru.edu.mirea.task6.factory_method.GoldGenerator;
import ru.edu.mirea.task6.factory_method.ItemGenerator;

public class FactoryMethodTest {
    @Test
    public void goldRewardGeneratorTest() {
        ItemGenerator generator = new GoldGenerator();
        generator.openReward();
    }

    @Test
    public void gemRewardGeneratorTest() {
        ItemGenerator generator = new GemGenerator();
        generator.openReward();
    }
}
