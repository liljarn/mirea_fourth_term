package ru.edu.mirea.task4;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;

public class ExecutorServiceImplTest {
    @Test
    void testExecuteTask() {
        ExecutorService threadPool = ExecutorServiceImpl.create(10);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> System.out.println("Start execution"));
        }
    }
}
