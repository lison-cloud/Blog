package by.bsuir.blog.mapper.impl;

import java.util.Arrays;
import java.util.List;

import by.bsuir.blog.mapper.ConnectionPool;
import by.bsuir.blog.mapper.CriteriaBuilder;
import by.bsuir.blog.mapper.CriteriaDelete;
import by.bsuir.blog.mapper.CriteriaInsert;
import by.bsuir.blog.mapper.CriteriaSelect;
import by.bsuir.blog.mapper.CriteriaUpdate;
import by.bsuir.blog.mapper.EntityManager;
import by.bsuir.blog.mapper.Query;
import by.bsuir.blog.mapper.QueryExecutor;
import by.bsuir.blog.mapper.Statement;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.TypedQuery;
import by.bsuir.blog.mapper.exception.MapperException;
import by.bsuir.blog.mapper.exception.QueryExecutorException;

public class EntityManagerImpl
        implements EntityManager {

    private CriteriaBuilder criteriaBuilder;
    private QueryExecutor queryExecutor;

    public EntityManagerImpl(ConnectionPool pool) {
        this.queryExecutor = QueryExecutorImpl.newInstance(pool);
        this.criteriaBuilder = CriteriaBuilderImpl.newInstance();
    }

    @Override
    public CriteriaBuilder criteriaBuilder() {
        return criteriaBuilder;
    }

    @Override
    public long add(Table table, Object obj) throws MapperException{
        CriteriaInsert ci = this.criteriaBuilder.createInsert(table);
        return this.createQuery(
                ci.values(obj)).generatedKey();
    }

    @Override
    public void update(Table table, Object obj) throws MapperException{
        CriteriaUpdate cu = this.criteriaBuilder.createUpdate(table);
        String tId = table.id();
        this.createQuery(
                cu.set(obj).where(
                        this.criteriaBuilder.equal(tId, table.get(tId).get(obj))));
    }

    @Override
    public <T> T find(Table table, Object id) throws MapperException {
        CriteriaSelect<T> cs = this.criteriaBuilder.createSelect(table);
        return this.createQuery(
                cs.select(
                        cs.where(this.criteriaBuilder.equal(table.id(), id))))
                .getSingleResult();
    }

    @Override
    public void remove(Table table, Object id) throws MapperException{
        CriteriaDelete cd = this.criteriaBuilder.createDelete(table);
        this.createQuery(
                cd.where(this.criteriaBuilder.equal(table.id(), id)));
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaSelect<T> cs) throws MapperException {
        return this.createTypedQuery0(cs.statement(), cs.table());
    }

    private <T> TypedQuery<T> createTypedQuery0(Statement st, Table table) throws MapperException {
        TypedQuery<T> typedQuery = null;
        try {
            typedQuery = this.queryExecutor.execute(st, table);
        } catch (QueryExecutorException e) {
            throw new MapperException(e);
        }
        return typedQuery;
    }

    @Override
    public Query createQuery(CriteriaUpdate cu) throws MapperException {
        return this.createQuery0(cu.statement());
    }

    private Query createQuery0(Statement st) throws MapperException {
        Query q = null;
        try {
            q = this.queryExecutor.executeUpdate(st);
        } catch (QueryExecutorException e) {
            throw new MapperException(e);
        }
        return q;
    }

    @Override
    public Query createQuery(CriteriaDelete cd) throws MapperException{
        return this.createQuery0(cd.statement());
    }

    @Override
    public Query createQuery(String s, List<Object> obj) throws MapperException{
        StatementImpl st = new StatementImpl(s, obj);
        return this.createQuery0(st);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String s, List<Object> obj, Table table) throws MapperException{
        StatementImpl st = new StatementImpl(s, obj);
        return this.createTypedQuery0(st, table);
    }

    @Override
    public Query createQuery(String s, Object obj) throws MapperException{
        StatementImpl st = new StatementImpl(s, Arrays.asList(obj));
        return this.createQuery0(st);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String s, Object obj, Table table) throws MapperException{
        StatementImpl st = new StatementImpl(s, Arrays.asList(obj));
        return this.createTypedQuery0(st, table);
    }

    @Override
    public Query createQuery(CriteriaInsert q) throws MapperException{
        return this.createQuery0(q.statement());
    }

}
