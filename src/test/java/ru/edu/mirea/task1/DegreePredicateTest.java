package ru.edu.mirea.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class DegreePredicateTest {
    @Test
    @DisplayName("test of degree of two")
    public void test_ShouldReturnTrue_whenDigitIsDegreeOfTwo() {
        DegreePredicate degreePredicate = new DegreePredicate();
        assertThat(degreePredicate.test(1024)).isTrue();
    }

    @Test
    @DisplayName("test of not degree of two")
    public void test_ShouldReturnFalse_whenDigitIsNotDegreeOfTwo() {
        DegreePredicate degreePredicate = new DegreePredicate();
        assertThat(degreePredicate.test(999)).isFalse();
    }

    @Test
    @DisplayName("test of lambda")
    public void lambdaTest() {
        Predicate<Integer> lamda = a -> (a & (a - 1)) == 0;
        assertThat(lamda.test(1024)).isTrue();
    }
}
