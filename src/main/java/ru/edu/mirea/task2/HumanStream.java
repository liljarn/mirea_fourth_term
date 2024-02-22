package ru.edu.mirea.task2;

import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class HumanStream {
    public static void reversedNameSort(List<Human> humanList) {
        humanList
                .stream()
                .sorted(Comparator.comparing(Human::firstName).reversed())
                .forEach(System.out::println);
    }

    public static void filterStream(List<Human> humanList) {
        humanList
                .stream()
                .filter(human -> human.age() > 20)
                .forEach(System.out::println);
    }

    public static void firstThreeStream(List<Human> humanList) {
        humanList
                .stream()
                .limit(3)
                .forEach(System.out::println);
    }

    public static String concatNamesStream(List<Human> humanList) {
        return humanList
                .stream()
                .map(Human::firstName)
                .collect(Collectors.joining(" "));
    }
}
