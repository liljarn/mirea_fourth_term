package ru.edu.mirea.task3;

import java.util.*;

public class SynchronizedSet<E> implements Set<E> {

    private final Set<E> set;

    public SynchronizedSet() {
        this.set = new HashSet<>();
    }

    @Override
    public synchronized int size() {
        return this.set.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return this.set.isEmpty();
    }

    @Override
    public synchronized boolean contains(Object o) {
        return this.set.contains(o);
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return this.set.iterator();
    }

    @Override
    public synchronized Object[] toArray() {
        return set.toArray();
    }

    @Override
    public synchronized  <T> T[] toArray(T[] ts) {
        return set.toArray(ts);
    }

    @Override
    public synchronized boolean add(E e) {
        return this.set.add(e);
    }

    @Override
    public synchronized boolean remove(Object o) {
        return this.set.remove(o);
    }

    @Override
    public synchronized boolean containsAll(Collection<?> collection) {
        return this.set.containsAll(collection);
    }

    @Override
    public synchronized boolean addAll(Collection<? extends E> collection) {
        return this.set.addAll(collection);
    }

    @Override
    public synchronized boolean retainAll(Collection<?> collection) {
        return this.set.retainAll(collection);
    }

    @Override
    public synchronized boolean removeAll(Collection<?> collection) {
        return this.set.removeAll(collection);
    }

    @Override
    public synchronized void clear() {
        this.set.clear();
    }
}
