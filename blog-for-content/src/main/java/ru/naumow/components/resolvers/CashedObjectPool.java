package ru.naumow.components.resolvers;

public interface CashedObjectPool<T> {

    T cashedOf(T subject);

    void dispose(T subject);

}
