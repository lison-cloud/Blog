package by.bsuir.blog.mapper.impl;

import java.util.Comparator;

import by.bsuir.blog.mapper.CriteriaDelete;
import by.bsuir.blog.mapper.Entry;
import by.bsuir.blog.mapper.Predicate;
import by.bsuir.blog.mapper.Statement;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.CriteriaDeleteException;

public class CriteriaDeleteImpl
        extends AbstractQueryImpl
        implements CriteriaDelete {

    private DeleteRoot deleteRoot;

    private CriteriaDeleteImpl(Table table) {
        this.deleteRoot = new DeleteRoot(table);
        this.deleteRoot.deleteFrom();
    }

    public static CriteriaDelete criteria(Table table) throws CriteriaDeleteException {
        if (table == null) {
            throw new CriteriaDeleteException("Table is null");
        }
        return new CriteriaDeleteImpl(table);
    }

    private static class DeleteRoot
            extends AbstractRoot {

        private static Comparator<String> c = new StatementPatternComparator() {
            {
                pattern.put(SQLStatement.DELETE_FROM, 1);
                pattern.put(SQLStatement.WHERE, 2);
            }
        };

        private DeleteRoot(Table table) {
            super(c, table);
        }

        private void deleteFrom() {
            Entry e = new EntryImpl(table.name());
            this.queryMap.put(SQLStatement.DELETE_FROM, e);
        }

        private void where(String exp, Object value) {
            EntryImpl e = new EntryImpl(exp, value);
            this.queryMap.put(SQLStatement.WHERE, e);
        }

    }

    @Override
    public Statement statement() {
        Statement st = AbstractCommonQuery.parse(this.deleteRoot);
        return st;
    }

    @Override
    public CriteriaDelete where(Predicate expression) {
        this.deleteRoot.where(expression.predicate(), expression.value());
        return this;
    }

}
