package by.bsuir.blog.mapper;

@FunctionalInterface
public interface Constructor<T> {
    T newInstance();
}
