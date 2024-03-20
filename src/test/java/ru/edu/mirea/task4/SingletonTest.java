package ru.edu.mirea.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.edu.mirea.hw5.EnumSingleton;
import ru.edu.mirea.hw5.LazySingleton;
import ru.edu.mirea.hw5.SimpleSingleton;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("Lazy initialization singleton test")
    public void differentVariables_shouldHaveSameLink_whenObjectCreatedByLazyInit() {
        LazySingleton firstSingleton = LazySingleton.getInstance();
        LazySingleton secondSingleton = LazySingleton.getInstance();
        assertThat(firstSingleton).isEqualTo(secondSingleton);
    }

    @Test
    @DisplayName("Enum singleton test")
    public void differentVariables_shouldHaveSameLink_whenObjectIsInEnum() {
        EnumSingleton firstSingleton = EnumSingleton.INSTANCE;
        EnumSingleton secondSingleton = EnumSingleton.INSTANCE;
        assertThat(firstSingleton).isEqualTo(secondSingleton);
    }

    @Test
    @DisplayName("Simple singleton test")
    public void differentVariables_shouldHaveSameLink_whenObjectIsStatic() {
        SimpleSingleton firstSingleton = SimpleSingleton.INSTANCE;
        SimpleSingleton secondSingleton = SimpleSingleton.INSTANCE;
        assertThat(firstSingleton).isEqualTo(secondSingleton);
    }
}
