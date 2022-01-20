package by.bsuir.blog.mapper;

@FunctionalInterface
public interface Getter<U, T> {

    T get(U u);
    
}
