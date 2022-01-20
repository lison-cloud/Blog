package by.bsuir.blog.mapper.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import by.bsuir.blog.mapper.ConnectionPool;
import by.bsuir.blog.mapper.exception.ConnectionPoolException;

public class ConnectionPoolImpl implements ConnectionPool, Runnable {

    private String driver, url, username, password;
    private int maxConnections;
    private boolean waitIfBusy;
    private Vector<Connection> availableConnections, busyConnections;
    private boolean connectionPending = false;

    public ConnectionPoolImpl(String driver, String url,
            String username, String password,
            int initialConnections,
            int maxConnections,
            boolean waitIfBusy)
            throws ConnectionPoolException {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxConnections = maxConnections;
        this.waitIfBusy = waitIfBusy;
        if (initialConnections > maxConnections) {
            initialConnections = maxConnections;
        }
        this.availableConnections = new Vector<>(initialConnections);
        this.busyConnections = new Vector<>();
        for (int i = 0; i < initialConnections; i++) {
            this.availableConnections.add(this.makeNewConnection());
        }
    }

    public synchronized Connection getConnection() throws ConnectionPoolException {
        if (!this.availableConnections.isEmpty()) {
            Connection existingConnection = this.availableConnections.lastElement();
            int lastIndex = availableConnections.size() - 1;
            availableConnections.removeElementAt(lastIndex);

            boolean isClosed = false;

            try {
                isClosed = existingConnection.isClosed();
            } catch (SQLException e) {
                throw new ConnectionPoolException(e);
            }

            if (isClosed) {
                notifyAll();
                return (this.getConnection());
            } else {
                busyConnections.addElement(existingConnection);
                return existingConnection;
            }
        } else {
            if ((totalConnections() < maxConnections) &&
                    !connectionPending) {
                this.makeBackgroundConnection();
            } else if (!waitIfBusy) {
                throw new ConnectionPoolException("Connection limit reached");
            }

            try {
                wait();
            } catch (InterruptedException e) {}
            return this.getConnection();
        }
    }

    private void makeBackgroundConnection() {
        connectionPending = true;
        Thread connectThread = new Thread(this);
        connectThread.start();
    }

    @Override
    public void run() {
        try {
            Connection connection = this.makeNewConnection();
            synchronized (this) {
                availableConnections.addElement(connection);
                connectionPending = false;
                notifyAll();
            }
        } catch (ConnectionPoolException e) {}
    }

    private Connection makeNewConnection() throws ConnectionPoolException {
        try {
            Class.forName(this.driver);
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Cannot find class for driver: " + this.driver,  e);
        } catch(SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    public synchronized void free(Connection connection) {
        this.busyConnections.removeElement(connection);
        this.availableConnections.addElement(connection);
        notifyAll();
    }

    private synchronized int totalConnections() {
        return (this.availableConnections.size() +
                this.busyConnections.size());
    }

    public synchronized void closeAll() throws ConnectionPoolException {
        this.closeConnections(availableConnections);
        this.availableConnections = new Vector<>();
        this.closeConnections(busyConnections);
        this.busyConnections = new Vector<>();
    }

    private synchronized void closeConnections(Vector<Connection> connections) throws ConnectionPoolException {
        try {
            for (int i = 0; i < connections.size(); i++) {
                Connection connection = connections.elementAt(i);
                if (!connection.isClosed()) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

}
