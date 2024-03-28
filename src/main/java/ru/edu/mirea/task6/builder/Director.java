package ru.edu.mirea.task6.builder;

import ru.edu.mirea.task6.builder.model.CarType;
import ru.edu.mirea.task6.builder.model.Color;
import ru.edu.mirea.task6.builder.model.Engine;

public class Director {
    public void constructSportsCar(Builder builder) {
        builder.setCarType(CarType.SPORTS_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(3.0, 0));
        builder.setColor(Color.RED);
    }

    public void constructCityCar(Builder builder) {
        builder.setCarType(CarType.CITY_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(1.2, 0));
        builder.setColor(Color.BLACK);
    }

    public void constructSUV(Builder builder) {
        builder.setCarType(CarType.SUV);
        builder.setSeats(4);
        builder.setEngine(new Engine(2.5, 0));
        builder.setColor(Color.WHITE);
    }
}
