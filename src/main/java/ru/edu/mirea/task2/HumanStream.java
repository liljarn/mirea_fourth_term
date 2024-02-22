package ru.edu.mirea.task2;

import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class HumanStream {
    public static List<Human> reversedNameSortStream(List<Human> humanList) {
        return humanList
                .stream()
                .sorted(Comparator.comparing(Human::firstName).reversed())
                .toList();
    }

    public static List<Human> filterStream(List<Human> humanList) {
        return humanList
                .stream()
                .filter(human -> human.age() > 20)
                .toList();
    }

    public static List<Human> firstThreeStream(List<Human> humanList) {
        return humanList
                .stream()
                .limit(3)
                .toList();
    }

    public static String concatNamesStream(List<Human> humanList) {
        return humanList
                .stream()
                .map(Human::firstName)
                .collect(Collectors.joining(" "));
    }
}
