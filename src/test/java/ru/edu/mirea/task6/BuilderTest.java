package ru.edu.mirea.task6;

import org.junit.jupiter.api.Test;
import ru.edu.mirea.task6.builder.Builder;
import ru.edu.mirea.task6.builder.CarBuilder;
import ru.edu.mirea.task6.builder.Director;

public class BuilderTest {
    @Test
    public void builderTest() {
        Director director = new Director();
        Builder builder = new CarBuilder();
        director.constructCityCar(builder);
        director.constructSportsCar(builder);
        director.constructSUV(builder);
    }
}
