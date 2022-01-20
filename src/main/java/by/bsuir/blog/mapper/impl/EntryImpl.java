package by.bsuir.blog.mapper.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import by.bsuir.blog.mapper.Entry;

public class EntryImpl
    implements Entry {

    private String exp;
    private List<Object> obj;

    public EntryImpl(String exp) {
        this.exp = exp;
    }

    public EntryImpl(String exp, Object obj) {
        this(exp);
        this.obj = new LinkedList<>();
        this.obj.add(obj);
    }

    public EntryImpl(List<Object> objects) {
        this.obj = objects;
    }

    public EntryImpl(String exp, List<Object> obj) {
        this.exp = exp;
        this.obj = obj;
    }

    public void addObject(Object object) {
        this.obj.add(object);
    }

    public void addAllObject(Collection<Object> c) {
        this.obj.add(c);
    }

    @Override
    public String exp() {
        return exp;
    }

    @Override
    public List<Object> objects() {
        return obj;
    }
    
}
