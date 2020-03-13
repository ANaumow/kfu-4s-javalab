package ru.naumow.repositories;

import java.util.Optional;

public interface CrudRepository<ID, V> {

    Optional<V> find(ID id);

    void update(V entity);

    void save(V entity);

    void delete(ID id);

}
