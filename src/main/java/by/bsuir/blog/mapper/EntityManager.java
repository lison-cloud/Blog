package by.bsuir.blog.mapper;

import java.util.List;

import by.bsuir.blog.mapper.exception.MapperException;


public interface EntityManager {

    CriteriaBuilder criteriaBuilder();

    Query createQuery(CriteriaInsert q) throws MapperException;

    Query createQuery(CriteriaUpdate q) throws MapperException;

    Query createQuery(CriteriaDelete q) throws MapperException;

    <T> TypedQuery<T> createQuery(CriteriaSelect<T> q) throws MapperException;

    Query createQuery(String s, Object obj) throws MapperException;

    Query createQuery(String s, List<Object> obj) throws MapperException;

    <T> TypedQuery<T> createQuery(String s, Object obj, Table table) throws MapperException;

    <T> TypedQuery<T> createQuery(String s, List<Object> obj, Table table) throws MapperException;

    long add(Table table, Object obj) throws MapperException;

    void update(Table table, Object obj) throws MapperException;

    <T> T find(Table table, Object id) throws MapperException;

    void remove(Table table, Object id) throws MapperException;

}
