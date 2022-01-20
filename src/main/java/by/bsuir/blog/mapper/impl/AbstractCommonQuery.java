package by.bsuir.blog.mapper.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;

import by.bsuir.blog.mapper.BaseQuery;
import by.bsuir.blog.mapper.Entry;
import by.bsuir.blog.mapper.Root;
import by.bsuir.blog.mapper.Statement;

public abstract class AbstractCommonQuery
        implements BaseQuery {

    public static Statement parse(Root root) {

        StringBuilder b = new StringBuilder();
        List<Object> val = new LinkedList<>();

        SortedMap<String, Entry> sm = root.queryPartMap();

        for (String p : sm.keySet()) {
            Entry e = sm.get(p);

            if (e == null) {
                continue;
            }
            b.append(p);
            if (e.exp() == null)
                continue;
            b.append(e.exp());
            if (e.objects() != null)
                val.addAll(e.objects());

        }

        return new StatementImpl(b.toString(), val);
    }

    protected static class SQLStatement {

        protected static final String SELECT = "SELECT ";
        protected static final String FROM = " FROM ";
        protected static final String WHERE = " WHERE ";
        protected static final String DELETE_FROM = "DELETE FROM ";
        protected static final String UPDATE = "UPDATE ";
        protected static final String SET = " SET ";
        protected static final String INSERT_INTO = "INSERT INTO ";
        protected static final String VALUES = " VALUES ";
        protected static final String ORDER_BY = " ORDER BY ";
        protected static final String DESC = " DESC ";
    }

}
