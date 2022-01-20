package by.bsuir.blog.mapper.impl;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import by.bsuir.blog.mapper.CriteriaUpdate;
import by.bsuir.blog.mapper.Entry;
import by.bsuir.blog.mapper.Field;
import by.bsuir.blog.mapper.Predicate;
import by.bsuir.blog.mapper.Statement;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.CriteriaUpdateException;

public class CriteriaUpdateImpl
        extends AbstractQueryImpl
        implements CriteriaUpdate {

    private UpdateRoot updateRoot;

    private CriteriaUpdateImpl(Table table) {
        this.updateRoot = new UpdateRoot(table);
        this.updateRoot.update();
    }

    public static CriteriaUpdate criteria(Table table) throws CriteriaUpdateException {
        if (table == null) {
            throw new CriteriaUpdateException("Table is null");
        }
        return new CriteriaUpdateImpl(table);
    }

    private static class UpdateRoot
            extends AbstractRoot {

        private static Comparator<String> c = new StatementPatternComparator() {
            {
                pattern.put(SQLStatement.UPDATE, 1);
                pattern.put(SQLStatement.SET, 2);
                pattern.put(SQLStatement.WHERE, 3);
            }
        };

        private UpdateRoot(Table table) {
            super(c, table);
        }

        private void update() {
            Entry e = new EntryImpl(table.name());
            this.queryMap.put(SQLStatement.UPDATE, e);
        }

        private void set(String exp, List<Object> values) {
            EntryImpl e = new EntryImpl(exp, values);
            this.queryMap.put(SQLStatement.SET, e);
        }

        private void where(String exp, Object value) {
            EntryImpl e = new EntryImpl(exp, value);
            this.queryMap.put(SQLStatement.WHERE, e);
        }

    }

    @Override
    public Statement statement() {
        Statement st = AbstractCommonQuery.parse(this.updateRoot);
        return st;
    }

    @Override
    public CriteriaUpdate set(Object obj) {

        if (obj == null) {
            throw new NullPointerException("Object is null");
        }

        Table t = this.updateRoot.table;
        List<Object> vals = new LinkedList<>();

        StringBuilder b = new StringBuilder();

        t.columns().stream().forEach(
                c -> {
                    Field<Object, Object> f = t.get(c);
                    if (f.get(obj) == null && !f.isNull())
                        throw new NullPointerException("field is null " + c);
                    vals.add(f.get(obj));
                    b.append(c).append("=?,");
                });

        b.deleteCharAt(b.length() - 1);
        this.updateRoot.set(b.toString(), vals);

        return this;
    }

    @Override
    public CriteriaUpdate where(Predicate expression) {
        this.updateRoot.where(expression.predicate(), expression.value());
        return this;
    }

}
