package by.bsuir.blog.mapper;

@FunctionalInterface
public interface Setter<U, T> {

    void set(U u, T t);

}
