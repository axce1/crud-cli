package repository;

import model.Skill;

import java.io.IOException;
import java.util.List;

public interface GenericRepository<T, ID> {
    T save(T t) throws IOException;

    void delete(T t) throws IOException;

    Skill findById(Long id) throws IOException;

    T update(T t) throws IOException;

    List<T> findAll() throws IOException;
}
