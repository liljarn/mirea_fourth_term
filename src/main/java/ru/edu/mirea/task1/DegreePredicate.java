package ru.edu.mirea.task1;

import java.util.function.Predicate;

public class DegreePredicate implements Predicate<Integer> {
    @Override
    public boolean test(Integer integer) {
        return (integer & (integer - 1)) == 0;
    }
}
