package ru.edu.mirea.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExecutorServiceImpl implements ExecutorService {
    private final AtomicBoolean canBeExecuted = new AtomicBoolean(false);
    private final BlockingQueue<Runnable> blockingQueue;
    private final ThreadWorker[] threads;
    private static final Logger LOGGER = LogManager.getLogger();

    private ExecutorServiceImpl(int threadNum) {
        if (threadNum <= 0) {
            throw new IllegalArgumentException("Pool of threads can't be below zero");
        }
        this.threads = new ThreadWorker[threadNum];
        blockingQueue = new LinkedBlockingQueue<>();
        canBeExecuted.set(true);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ThreadWorker();
            threads[i].start();
        }
    }

    public static ExecutorServiceImpl create(int threads) {
        return new ExecutorServiceImpl(threads);
    }

    public void start() {
        canBeExecuted.set(true);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ThreadWorker();
            threads[i].start();
        }
    }

    @Override
    public void shutdown() {
        canBeExecuted.set(true);
        for (ThreadWorker workerThread : threads) {
            workerThread.interrupt();
        }
    }

    @Override
    public List<Runnable> shutdownNow() {
        canBeExecuted.set(true);
        List<Runnable> unfinishedTasks = new ArrayList<>();
        for (ThreadWorker workerThread : threads) {
            workerThread.interrupt();
            unfinishedTasks.addAll(blockingQueue);
        }
        return unfinishedTasks;
    }

    @Override
    public boolean isShutdown() {
        return canBeExecuted.get();
    }

    @Override
    public boolean isTerminated() {
        return canBeExecuted.get() && blockingQueue.isEmpty();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long endTime = System.nanoTime() + unit.toNanos(timeout);
        while (System.nanoTime() < endTime && !blockingQueue.isEmpty()) {
            TimeUnit.MILLISECONDS.sleep(100);
        }
        return isTerminated();
    }

    @Override
    public <T> Future<T> submit(Callable<T> callable) {
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return null;
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long l, TimeUnit timeUnit) throws InterruptedException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public void execute(Runnable runnable) {
        if (!canBeExecuted.get()) {
            throw new IllegalStateException("ThreadPool not started");
        }
        try {
            blockingQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private final class ThreadWorker extends Thread {
        @Override
        public void run() {
            while (canBeExecuted.get() || !blockingQueue.isEmpty()) {
                Runnable runnable;
                while ((runnable = blockingQueue.poll()) != null) {
                    runnable.run();
                    System.out.println("Was executed at: " + Thread.currentThread().getId());
                }
            }
        }
    }
}
