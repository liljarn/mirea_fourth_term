package ru.edu.mirea.hw5;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum EnumSingleton {
    INSTANCE("Initial class info");

    @Setter
    private String info;

    EnumSingleton(String info) {
        this.info = info;
    }

    public EnumSingleton getInstance() {
        return INSTANCE;
    }
}
