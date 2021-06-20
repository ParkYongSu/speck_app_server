package paidagogos.speck.repository;

import java.util.List;
import java.util.Optional;

public interface DataRepository<T> {
    Optional<List<T>> findAll();

    Optional<T> findById(String id);
}
