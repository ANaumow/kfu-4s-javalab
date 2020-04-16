package ru.naumow.components.resolvers;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractCashedObjectPool<T> implements CashedObjectPool<T> {

    private final List<T> cash;

    public AbstractCashedObjectPool() {
        cash = new CopyOnWriteArrayList<>();
    }

    @Override
    public void dispose(T subject) {
        for (T t : cash) {
            if (t.equals(subject)) {
                cash.remove(subject);
            }
        }
    }

    @Override
    public T cashedOf(T subject) {
        for (T t : cash) {
            if (t.equals(subject)) {
                return t;
            }
        }
        cash.add(subject);
        return subject;
    }

    @PreDestroy
    public void destroy() {
        cash.forEach(Object::notifyAll);
    }

}
