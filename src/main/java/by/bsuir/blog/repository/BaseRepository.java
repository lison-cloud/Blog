package by.bsuir.blog.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import by.bsuir.blog.repository.exception.RepositoryException;

public interface BaseRepository<T extends Serializable> {

    List<T> getAll() throws RepositoryException;

    long add(T t) throws RepositoryException;

    Optional<T> find(Object id) throws RepositoryException;

    void update(T t) throws RepositoryException;

    void remove(T t) throws RepositoryException;

    void removeById(Object id) throws RepositoryException;

}