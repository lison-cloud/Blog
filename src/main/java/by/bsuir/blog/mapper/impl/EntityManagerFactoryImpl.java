package by.bsuir.blog.mapper.impl;

import by.bsuir.blog.mapper.ConnectionPool;
import by.bsuir.blog.mapper.EntityManager;
import by.bsuir.blog.mapper.EntityManagerFactory;

public class EntityManagerFactoryImpl
        implements EntityManagerFactory {

    private static EntityManagerFactory instance;
    private static EntityManager entityManager;

    private final ConnectionPool pool;

    private EntityManagerFactoryImpl(ConnectionPool pool) {
        this.pool = pool;
    }

    public static EntityManagerFactory newInstance(ConnectionPool pool) {
        if (instance == null) {
            synchronized (EntityManagerFactoryImpl.class) {
                if (instance == null) {
                    instance = new EntityManagerFactoryImpl(pool);
                }
            }
        }
        return instance;
    }

    @Override
    public EntityManager entityManager() {
        if (entityManager == null) {
            synchronized (EntityManagerFactoryImpl.class) {
                if (entityManager == null) {
                    entityManager = new EntityManagerImpl(pool);
                }
            }
        }
        return entityManager;
    }

}
