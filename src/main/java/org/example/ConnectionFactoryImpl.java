package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {

    private final String url;
    private final String user;
    private final String password;

    public ConnectionFactoryImpl(String url,
                                 String user,
                                 String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
