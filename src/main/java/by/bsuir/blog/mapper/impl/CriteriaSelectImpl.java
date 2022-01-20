package by.bsuir.blog.mapper.impl;

import java.util.Comparator;

import by.bsuir.blog.mapper.CriteriaSelect;
import by.bsuir.blog.mapper.Entry;
import by.bsuir.blog.mapper.Predicate;
import by.bsuir.blog.mapper.Statement;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.CriteriaSelectException;
import by.bsuir.blog.mapper.util.QueryUtils;

public class CriteriaSelectImpl<T>
        extends AbstractQueryImpl
        implements CriteriaSelect<T> {

    private SelectRoot selectRoot;

    private CriteriaSelectImpl(Table table) {
        this.selectRoot = new SelectRoot(table);
        this.selectRoot.from();
    }

    public static <T> CriteriaSelect<T> criteria(Table table) throws CriteriaSelectException {
        if (table == null) {
            throw new CriteriaSelectException("Table is null");
        }
        return new CriteriaSelectImpl<>(table);
    }

    private static class SelectRoot
            extends AbstractRoot {

        private static Comparator<String> c = new StatementPatternComparator() {
            {
                pattern.put(SQLStatement.SELECT, 1);
                pattern.put(SQLStatement.FROM, 2);
                pattern.put(SQLStatement.WHERE, 3);
                pattern.put(SQLStatement.ORDER_BY, 4);
            }
        };

        private SelectRoot(Table table) {
            super(c, table);
        }

        private void select() {
            String exp = QueryUtils.cols(table);
            Entry e = new EntryImpl(exp);
            this.queryMap.put(SQLStatement.SELECT, e);
        }

        private void from() {
            Entry e = new EntryImpl(table.name());
            this.queryMap.put(SQLStatement.FROM, e);
        }

        private void where(String exp, Object value) {
            Entry e = new EntryImpl(exp, value);
            this.queryMap.put(SQLStatement.WHERE, e);
        }

        private void orderBy(String column) {
            Entry e = new EntryImpl(column);
            this.queryMap.put(SQLStatement.ORDER_BY, e);
        }

        private void descOrderBy(String column) {
            Entry e = new EntryImpl(column + SQLStatement.DESC);
            this.queryMap.put(SQLStatement.ORDER_BY, e);
        }

    }

    @Override
    public Statement statement() {
        Statement st = AbstractCommonQuery.parse(this.selectRoot);
        return st;
    }

    @Override
    public CriteriaSelect<T> select(CriteriaSelect<T> criteria) {
        this.selectRoot.select();
        return this;
    }

    @Override
    public CriteriaSelect<T> where(Predicate expr) {
        this.selectRoot.where(expr.predicate(), expr.value());
        return this;
    }

    @Override
    public CriteriaSelect<T> select() {
        this.selectRoot.select();
        return this;
    }

    @Override
    public CriteriaSelect<T> orderBy(String column, boolean asc) {
        if (column == null) {
            throw new NullPointerException("column is null");
        }

        if (!asc) {
            this.selectRoot.descOrderBy(column);
        } else {
            this.selectRoot.orderBy(column);
        }
        return this;
    }

    @Override
    public Table table() {
        return this.selectRoot.table;
    }

}
