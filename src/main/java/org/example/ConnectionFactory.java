package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

    /**
     * @return new connection to database
     * @throws SQLException if any SQL exception occurs
     */
    Connection createConnection() throws SQLException;

}
