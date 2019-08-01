package org.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SqlDBRepositoryTest {

    private final static String TEST_NAME = "test name";

    private SqlDBRepository repository;
    private ConnectionFactory mockedConnectionFactory;

    @Before
    public void setUp() {
        mockedConnectionFactory = Mockito.mock(ConnectionFactory.class);
        repository = new SqlDbRepositoryImpl(mockedConnectionFactory);
    }

    @Test
    public void testSelectNoEntry() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.next()).thenReturn(false);
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(statement.executeQuery()).thenReturn(resultSet);
        mockConnection(statement);
        assertNull(repository.findByName(TEST_NAME));
    }

    private void mockConnection(PreparedStatement statement) throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        Mockito.when(mockedConnectionFactory.createConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(statement);
    }

    @Test
    public void testSelect() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getString(Mockito.anyInt())).thenReturn("last name").thenThrow(new RuntimeException());
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(statement.executeQuery()).thenReturn(resultSet);
        mockConnection(statement);

        Account actual = repository.findByName(TEST_NAME);
        assertEquals(TEST_NAME, actual.getName());
        assertEquals("last name", actual.getLastName());
    }
}
