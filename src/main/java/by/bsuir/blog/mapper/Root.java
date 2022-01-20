package by.bsuir.blog.mapper;

import java.util.SortedMap;

public interface Root {

    SortedMap<String, Entry> queryPartMap();

    Table entityTable();

}
