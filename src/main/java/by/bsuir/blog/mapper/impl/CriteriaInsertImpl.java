package by.bsuir.blog.mapper.impl;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import by.bsuir.blog.mapper.CriteriaInsert;
import by.bsuir.blog.mapper.Entry;
import by.bsuir.blog.mapper.Field;
import by.bsuir.blog.mapper.Statement;
import by.bsuir.blog.mapper.Table;
import by.bsuir.blog.mapper.exception.CriteriaInsertException;

public class CriteriaInsertImpl
        extends AbstractCommonQuery
        implements CriteriaInsert {

    private InsertRoot insertRoot;

    public static CriteriaInsert getCriteria(Table table) throws CriteriaInsertException {
        if (table == null) {
            throw new CriteriaInsertException("Table is null");
        }
        return new CriteriaInsertImpl(table);
    }

    private CriteriaInsertImpl(Table table) {
        this.insertRoot = new InsertRoot(table);
    }

    private static class InsertRoot
            extends AbstractRoot {

        private static Comparator<String> c = new StatementPatternComparator() {

            {
                pattern.put(SQLStatement.INSERT_INTO, 1);
                pattern.put(SQLStatement.VALUES, 2);
            }

        };

        private InsertRoot(Table table) {
            super(c, table);
        }

        private void insert(String exp) {
            Entry e = new EntryImpl(exp);
            this.queryMap.put(SQLStatement.INSERT_INTO, e);
        }

        private void values(String exp, List<Object> vals) {
            EntryImpl e = new EntryImpl(exp, vals);
            super.queryMap.put(SQLStatement.VALUES, e);
        }

    }

    @Override
    public Statement statement() {
        Statement st = AbstractCommonQuery.parse(this.insertRoot);
        return st;
    }

    @Override
    public CriteriaInsert values(Object obj) {

        if (obj == null) {
            throw new NullPointerException("Object is null");
        }

        Table t = this.insertRoot.table;

        StringBuilder insert = new StringBuilder(t.name()).append("(");
        StringBuilder values = new StringBuilder("(");

        List<Object> vals = new LinkedList<>();

        t.columns().stream().filter(s -> !s.equals(t.id())).forEach(
                c -> {
                    Field<Object, Object> f = t.get(c);
                    if (f.get(obj) == null && !f.isNull())
                        throw new NullPointerException("Field is null " + c);

                    insert.append(c).append(",");
                    values.append("?,");
                    vals.add(f.get(obj));
                });

        insert.replace(insert.length() - 1, insert.length(), ")");
        values.replace(values.length() - 1, values.length(), ")");

        this.insertRoot.insert(insert.toString());
        this.insertRoot.values(values.toString(), vals);

        return this;
    }

}
