package by.bsuir.blog.mapper;

import by.bsuir.blog.mapper.exception.QueryExecutorException;

public interface QueryExecutor {
    
    Query executeUpdate(Statement st) throws QueryExecutorException;

    <T> TypedQuery<T> execute(Statement st, Table table) throws QueryExecutorException;
}
