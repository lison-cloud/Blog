package by.bsuir.blog.mapper.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import by.bsuir.blog.mapper.ConnectionPool;
import by.bsuir.blog.mapper.exception.ConnectionPoolException;
import by.bsuir.blog.mapper.exception.ConnectionPoolFactoryException;
import by.bsuir.blog.mapper.impl.ConnectionPoolImpl;

public class ConnectionPoolFactory {
    public static ConnectionPool init(String fileName) throws ConnectionPoolFactoryException{
        if(fileName==null)
            throw new ConnectionPoolFactoryException("filename to property file is null");
        ConnectionPool pool = null;
        try (InputStream input = resource(fileName)) {
            Properties prop = new Properties();
            prop.load(input);

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            String init = prop.getProperty("initialConnections");
            String max = prop.getProperty("maxConnections");
            String waitIfBusy = prop.getProperty("waitIfBusy");

            int in = Integer.parseInt(init);
            int ma = Integer.parseInt(max);
            boolean w = Boolean.parseBoolean(waitIfBusy);

            pool = new ConnectionPoolImpl(driver,url,username,password,in,ma, w);
        } catch (IOException | ConnectionPoolException e) {
            throw new ConnectionPoolFactoryException(e);
        }
        return pool;
    }

    private static InputStream resource(String name) {
        return ConnectionPoolFactory.class.getClassLoader().getResourceAsStream(name);
    }
}
