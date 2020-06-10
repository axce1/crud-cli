package repository;

public interface GenericRepository<T, ID> {
    T create(T t);

    void delete(T t);

    T find(T t);

    T update(T t);
}
