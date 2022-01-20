package by.bsuir.blog.metadata.impl;

import by.bsuir.blog.mapper.Field;
import by.bsuir.blog.mapper.Getter;
import by.bsuir.blog.mapper.Setter;

public class DefaultField<U, T>
    implements Field<U, T> {

    private Getter<U,T> getter;
    private Setter<U,T> setter;
    private Class<T> clazz; 
    private boolean isNull;

    public DefaultField(Getter<U,T> getter, 
                        Setter<U,T> setter,
                        Class<T> clazz,
                        boolean isDefault){
        this.getter = getter;
        this.setter = setter;
        this.clazz = clazz;
        this.isNull = isDefault;
    }   

    @Override
    public Class<T> fClass(){
        return clazz;
    }

    @Override
    public T get(U u) {
        return getter.get(u);
    }

    @Override
    public void set(U u, T t) {
        setter.set(u, t);
    }

    @Override
    public boolean isNull() {
        return this.isNull;
    }
    
}
