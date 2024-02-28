package ru.edu.mirea.task3;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SynchronizedSetTest {
    @Test
    public void synchronizedSet_shouldNotHaveRaceCondition() {
        SynchronizedSet<Integer> set = new SynchronizedSet<>();
        for (int i = 0; i < 1000; i++) {
            set.add(i);
        }
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                set.remove(i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 500; i < 1000; i++) {
                set.remove(i);
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        assertEquals(0, set.size());
    }

    @Test
    public void set_shouldHaveRaceCondition() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            set.add(i);
        }
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                set.remove(i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 500; i < 1000; i++) {
                set.remove(i);
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        assertThat(set.size()).isNotEqualTo(0);
    }
}
