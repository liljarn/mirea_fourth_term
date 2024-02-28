package ru.edu.mirea.task3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LockListTest {
    @Test
    public void lockList_shouldNotHaveRaceCondition() {
        LockList<Integer> list = new LockList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                list.remove((Object) i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 500; i < 1000; i++) {
                list.remove((Object) i);
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
        assertEquals(0, list.size());
    }

    @Test
    public void list_shouldHaveRaceCondition() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                list.remove((Object) i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 500; i < 1000; i++) {
                list.remove((Object) i);
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
        assertThat(list.size()).isNotEqualTo(0);
    }
}
