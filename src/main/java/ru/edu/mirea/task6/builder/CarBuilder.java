package ru.edu.mirea.task6.builder;

import ru.edu.mirea.task6.builder.model.Car;
import ru.edu.mirea.task6.builder.model.CarType;
import ru.edu.mirea.task6.builder.model.Color;
import ru.edu.mirea.task6.builder.model.Engine;

public class CarBuilder implements Builder {
    private CarType type;
    private int seats;
    private Engine engine;
    private Color color;

    public Car getResult() {
        return new Car(type, seats, engine, color);
    }

    @Override
    public void setCarType(CarType type) {
        this.type = type;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
