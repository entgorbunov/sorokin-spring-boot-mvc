package sorokin.java.course.service;

import java.util.List;
import java.util.Optional;

public interface Service<E, K> {
    void delete(K k);

    Optional<E> findById(K k);

    List<E> findAll();

    Optional<E> update(E e);

    E create(E e);
}
