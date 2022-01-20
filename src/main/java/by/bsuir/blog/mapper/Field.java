package by.bsuir.blog.mapper;

public interface Field<U,T> {
    
    T get(U u);

    void set(U u,T t);

    Class<T> fClass();

    boolean isNull();
}
