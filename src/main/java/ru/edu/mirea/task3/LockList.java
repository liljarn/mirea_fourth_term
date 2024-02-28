package ru.edu.mirea.task3;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockList<E> implements List<E> {
    private final List<E> list;
    private final Lock lock = new ReentrantLock();

    public LockList() {
        this.list = new ArrayList<>();
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return list.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return list.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean contains(Object o) {
        lock.lock();
        try {
            return list.contains(o);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Iterator<E> iterator() {
        lock.lock();
        try {
            return list.iterator();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Object[] toArray() {
        lock.lock();
        try {
            return list.toArray();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        lock.lock();
        try {
            return list.toArray(ts);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean add(E e) {
        lock.lock();
        try {
            return list.add(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean remove(Object o) {
        lock.lock();
        try {
            return list.remove(o);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        lock.lock();
        try {
            return list.containsAll(collection);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        lock.lock();
        try {
            return list.addAll(collection);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        lock.lock();
        try {
            return list.addAll(collection);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        lock.lock();
        try {
            return list.removeAll(collection);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        lock.lock();
        try {
            return retainAll(collection);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            list.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E get(int i) {
        lock.lock();
        try {
            return list.get(i);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E set(int i, E e) {
        lock.lock();
        try {
            return list.set(i, e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void add(int i, E e) {
        lock.lock();
        try {
            list.add(i, e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E remove(int i) {
        lock.lock();
        try {
            if (list.isEmpty()) {
                throw new RuntimeException();
            }
            return list.remove(i);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int indexOf(Object o) {
        lock.lock();
        try {
            return list.indexOf(o);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        lock.lock();
        try {
            return list.lastIndexOf(o);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        lock.lock();
        try {
            return list.listIterator();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        lock.lock();
        try {
            return list.listIterator(i);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<E> subList(int i, int i1) {
        lock.lock();
        try {
            return list.subList(i, i1);
        } finally {
            lock.unlock();
        }
    }
}
