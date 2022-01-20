package by.bsuir.blog.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import by.bsuir.blog.mapper.CriteriaBuilder;
import by.bsuir.blog.mapper.CriteriaSelect;
import by.bsuir.blog.mapper.EntityManager;
import by.bsuir.blog.mapper.EntityManagerFactory;
import by.bsuir.blog.mapper.Metadata;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.ConnectionPoolFactoryException;
import by.bsuir.blog.mapper.exception.MapperException;
import by.bsuir.blog.mapper.impl.EntityManagerFactoryImpl;
import by.bsuir.blog.mapper.util.ConnectionPoolFactory;
import by.bsuir.blog.metadata.BlogMetadata;
import by.bsuir.blog.repository.exception.RepositoryException;

public abstract class AbstractBaseRepository<T extends Serializable>
        implements BaseRepository<T> {

    private static EntityManagerFactory factory;

    private static Metadata appMetadata = BlogMetadata.newInstance();

    public static void initRepository(String fileName) throws RepositoryException {
        try {
            factory = EntityManagerFactoryImpl.newInstance(
                    ConnectionPoolFactory.init(fileName));
        } catch (ConnectionPoolFactoryException e) {
            throw new RepositoryException("Failed to create connection pool", e);
        }
    }

    protected EntityManager entityManager = factory.entityManager();

    protected final Table table;

    protected AbstractBaseRepository(Class<T> entityClass) {
        this.table = appMetadata.entityTable(entityClass);
    }

    @Override
    public List<T> getAll() throws RepositoryException {
        CriteriaBuilder cb = this.entityManager.criteriaBuilder();
        CriteriaSelect<T> cs = cb.createSelect(this.table);

        try {
            return this.entityManager.createQuery(cs.select()).getResultList();
        } catch (MapperException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public long add(T t) throws RepositoryException {
        try {
            return this.entityManager.add(table, t);
        } catch (MapperException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void remove(T t) throws RepositoryException {
        this.removeById(this.table.get(table.id()).get(t));
    }

    @Override
    public Optional<T> find(Object id) throws RepositoryException {
        T t = null;
        try {
            t = this.entityManager.find(this.table, id);
        } catch (MapperException e) {
            throw new RepositoryException(e);
        }
        return Optional.ofNullable(t);
    }

    @Override
    public void removeById(Object id) throws RepositoryException {
        try {
            this.entityManager.remove(this.table, id);
        } catch (MapperException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(T t) throws RepositoryException {
        try {
            this.entityManager.update(this.table, t);
        } catch (MapperException e) {
            throw new RepositoryException(e);
        }
    }

}
