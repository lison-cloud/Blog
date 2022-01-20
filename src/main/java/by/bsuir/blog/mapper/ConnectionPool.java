package by.bsuir.blog.mapper;

import java.sql.Connection;

import by.bsuir.blog.mapper.exception.ConnectionPoolException;

public interface ConnectionPool {

    Connection getConnection() throws ConnectionPoolException;

    void free(Connection connection);

    void closeAll() throws ConnectionPoolException;

}
