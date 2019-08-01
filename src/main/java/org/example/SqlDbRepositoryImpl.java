package org.example;

import java.sql.*;

public class SqlDbRepositoryImpl implements SqlDBRepository {

    //queries assume that table name and columns are not editable
    private static final String SELECT_QUERY = "select lastName from accounts where firstName = ?";
    private static final String UPDATE_QUERY = "update accounts set lastName = ? where firstName = ?";

    private final ConnectionFactory connectionFactory;

    public SqlDbRepositoryImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Account findByName(String name) throws SQLException {
        try (Connection connection = connectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setString(0, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String lastName = rs.getString(0);
                return new Account(name, lastName);
            }
            return null;
        }
    }

    @Override
    public void changeLastName(String name, String newLastName) throws SQLException {
        try (Connection connection = connectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(0, newLastName);
            statement.setString(1, name);
            int res = statement.executeUpdate();
        }
    }
}
