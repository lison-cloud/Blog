package by.bsuir.blog.mapper.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public abstract class StatementPatternComparator
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