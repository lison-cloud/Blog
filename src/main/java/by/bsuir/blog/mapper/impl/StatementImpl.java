package by.bsuir.blog.mapper.impl;

import java.util.List;

import by.bsuir.blog.mapper.Statement;

public class StatementImpl
    implements Statement {

    private final String st;
    private final List<Object> vals;

    public StatementImpl(String st, List<Object> vals) {
        this.st = st;
        this.vals = vals;
    }

    @Override
    public String statement() {
        return st;
    }

    @Override
    public List<Object> statementValue() {
        return vals;
    }
    
}
