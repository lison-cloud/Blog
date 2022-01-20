package by.bsuir.blog.mapper.impl;

import by.bsuir.blog.mapper.Predicate;
import by.bsuir.blog.mapper.exception.PredicateException;

public class PredicateImpl
    implements Predicate {
    
    private Object value;
    private String predicate;
    
    private PredicateImpl(String predicate, Object object) {
        this.predicate = predicate;
        this.value = object;
    }

    public static Predicate getPredicate(String field, Object object, String exp) throws PredicateException {
        if(field==null || exp==null) {
            throw new PredicateException("field or exp are null");
        }
        String predicate = new StringBuilder(field).append(exp).append("?").toString();
        return new PredicateImpl(predicate, object);
    }

    @Override
    public String predicate() {
        return this.predicate;
    }

    @Override
    public Object value() {
        return this.value;
    }
    
}
