package ru.edu.mirea.task6.builder;

import ru.edu.mirea.task6.builder.model.CarType;
import ru.edu.mirea.task6.builder.model.Color;
import ru.edu.mirea.task6.builder.model.Engine;

public interface Builder {
    void setCarType(CarType type);

    void setSeats(int seats);

    void setEngine(Engine engine);

    void setColor(Color color);
}
