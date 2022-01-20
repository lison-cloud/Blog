package by.bsuir.blog.mapper.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import by.bsuir.blog.mapper.Entry;
import by.bsuir.blog.mapper.Root;
import by.bsuir.blog.mapper.Table;

public abstract class AbstractRoot
        implements Root {

    protected SortedMap<String, Entry> queryMap;

    protected Table table;

    protected AbstractRoot(Comparator<String> comp, Table table) {
        this.table = table;
        this.queryMap = new TreeMap<>(comp);
    }

    protected List<Object> listValues() {
        List<Object> list = new LinkedList<>();
        for (Entry e : this.queryMap.values()) {
            list.addAll(e.objects());
        }
        return list;
    }

    @Override
    public SortedMap<String, Entry> queryPartMap() {
        return queryMap;
    }

    @Override
    public Table entityTable() {
        return this.table;
    }

    protected static abstract class StatementPatternComparator
            implements Comparator<String> {

        protected Map<String, Integer> pattern = new HashMap<>();

        @Override
        public int compare(String o1, String o2) {
            int i1 = pattern.get(o1);
            int i2 = pattern.get(o2);
            if (i1 > i2)
                return 1;
            else if (i1 < i2)
                return -1;
            return 0;
        }

    }

}
