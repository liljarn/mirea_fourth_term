package ru.edu.mirea.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HumanStreamTest {
    private final List<Human> list = List.of(
            new Human(15, "Vasya", "Afterlife", LocalDate.of(2008, Month.SEPTEMBER, 1), 54),
            new Human(23, "Kirill", "Vasiliev", LocalDate.of(2000, Month.MARCH, 3), 85),
            new Human(45, "Alexey", "Frolov", LocalDate.of(1979, Month.JANUARY, 9), 101),
            new Human(19, "Ivan", "Manov", LocalDate.of(2004, Month.AUGUST, 16), 68),
            new Human(21, "Aloha", "Dance", LocalDate.of(2002, Month.OCTOBER, 21), 75)
    );

    private final List<Human> sortedList = List.of(
            new Human(15, "Vasya", "Afterlife", LocalDate.of(2008, Month.SEPTEMBER, 1), 54),
            new Human(23, "Kirill", "Vasiliev", LocalDate.of(2000, Month.MARCH, 3), 85),
            new Human(19, "Ivan", "Manov", LocalDate.of(2004, Month.AUGUST, 16), 68),
            new Human(21, "Aloha", "Dance", LocalDate.of(2002, Month.OCTOBER, 21), 75),
            new Human(45, "Alexey", "Frolov", LocalDate.of(1979, Month.JANUARY, 9), 101)
    );

    @Test
    @DisplayName("reversedNameSortTestStream")
    public void reverseNameSortStream_shouldReturnListSortedByNamesInReversedOrder() {
        List<Human> newList = HumanStream.reversedNameSortStream(list);
        newList.forEach(System.out::println);
        assertThat(newList).isEqualTo(sortedList);
    }

    @Test
    @DisplayName("filterStreamTest")
    public void filterStream_shouldReturnListFilteredByAge() {
        List<Human> newList = HumanStream.filterStream(list);
        newList.forEach(System.out::println);
        assertThat(newList).isEqualTo(List.of(
                new Human(23, "Kirill", "Vasiliev", LocalDate.of(2000, Month.MARCH, 3), 85),
                new Human(45, "Alexey", "Frolov", LocalDate.of(1979, Month.JANUARY, 9),101),
                new Human(21, "Aloha", "Dance", LocalDate.of(2002, Month.OCTOBER, 21), 75)
        ));
    }

    @Test
    @DisplayName("firstThreeStreamTest")
    public void firstThreeStream_shouldReturnListOfFirstThreeElements() {
        List<Human> newList = HumanStream.firstThreeStream(list);
        newList.forEach(System.out::println);
        assertThat(newList).isEqualTo(List.of(
                new Human(15, "Vasya", "Afterlife", LocalDate.of(2008, Month.SEPTEMBER, 1), 54),
                new Human(23, "Kirill", "Vasiliev", LocalDate.of(2000, Month.MARCH, 3), 85),
                new Human(45, "Alexey", "Frolov", LocalDate.of(1979, Month.JANUARY, 9), 101)
        ));
    }

    @Test
    @DisplayName("concatNamesStreamTest")
    public void concatNamesStream_shouldReturnStringOfAllNames() {
        String names = HumanStream.concatNamesStream(list);
        System.out.println(names);
        assertThat(names).isEqualTo("Vasya Kirill Alexey Ivan Aloha");
    }
}
