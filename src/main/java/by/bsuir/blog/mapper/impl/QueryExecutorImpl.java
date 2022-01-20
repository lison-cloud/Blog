package by.bsuir.blog.mapper.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import by.bsuir.blog.mapper.ConnectionPool;
import by.bsuir.blog.mapper.Field;
import by.bsuir.blog.mapper.Query;
import by.bsuir.blog.mapper.QueryExecutor;
import by.bsuir.blog.mapper.Statement;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.TypedQuery;
import by.bsuir.blog.mapper.exception.QueryExecutorException;

public class QueryExecutorImpl implements QueryExecutor {

    private static QueryExecutor instance;

    private final ConnectionPool pool;

    private QueryExecutorImpl(ConnectionPool pool) {
        this.pool = pool;
    }

    public static QueryExecutor newInstance(ConnectionPool pool) {
        if (instance == null) {
            synchronized (QueryExecutorImpl.class) {
                if (instance == null) {
                    instance = new QueryExecutorImpl(pool);
                }
            }
        }
        return instance;
    }

    public <T> TypedQuery<T> execute(Statement st, Table table) throws QueryExecutorException {
        Connection con = null;
        PreparedStatement ps = null;
        TypedQuery<T> typedQuery = null;
        try {
            con = pool.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(st.statement());

            this.prepareStatement(ps, st.statementValue());

            con.commit();

            try (ResultSet set = ps.executeQuery()) {
                typedQuery = new TypedQueryImpl<>(this.initObject(set, table));
            }
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ignored) {
            }
            throw new QueryExecutorException(e);
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                throw new QueryExecutorException(e);
            }
            pool.free(con);
        }
        return typedQuery;
    }

    private <T> LinkedList<T> initObject(ResultSet set, Table table) throws QueryExecutorException {
        LinkedList<T> list = new LinkedList<>();
        try {
            while (set.next()) {
                T object = (T) table.constructor().newInstance();
                for (String str : table.columns()) {
                    Field<Object, Object> f = table.get(str);
                    Object obj = set.getObject(str, f.fClass());
                    f.set(object, obj);
                }
                list.add(object);
            }
        } catch (Exception e) {
            throw new QueryExecutorException(e);
        }
        return list;
    }

    public Query executeUpdate(Statement st) throws QueryExecutorException {
        Connection con = null;
        PreparedStatement ps = null;
        QueryImpl query = new QueryImpl();

        try {
            con = pool.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(st.statement(), java.sql.Statement.RETURN_GENERATED_KEYS);

            this.prepareStatement(ps, st.statementValue());

            ps.executeUpdate();

            con.commit();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long key = keys.getLong(1);
                    query.generatedKeys = key > 0 ? key : -1;
                }
            }
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ignored) {
            }
            throw new QueryExecutorException(e);
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                throw new QueryExecutorException(e);
            }
            pool.free(con);
        }
        return query;
    }

    private void prepareStatement(PreparedStatement ps, List<Object> values) throws QueryExecutorException {
        try {
            for (int i = 1; i <= values.size(); i++) {
                ps.setObject(i, values.get(i - 1));
            }
        } catch (Exception e) {
            throw new QueryExecutorException(e);
        }
    }

    private static class QueryImpl
            implements Query {

        private long generatedKeys;

        @Override
        public long generatedKey() {
            return this.generatedKeys;
        }

    }

    private static class TypedQueryImpl<T>
            extends QueryImpl
            implements TypedQuery<T> {

        private LinkedList<T> resultList;

        private TypedQueryImpl(LinkedList<T> list) {
            this.resultList = list;
        }

        @Override
        public T getSingleResult() {
            return !resultList.isEmpty() ? resultList.getFirst() : null;
        }

        @Override
        public List<T> getResultList() {
            return resultList;
        }

    }

}
