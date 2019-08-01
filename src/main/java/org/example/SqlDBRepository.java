package org.example;

import java.sql.SQLException;

/**
 * Allow search and update accounts in repository.
 * The interface assumes that for repository is used a database
 * but actually implementations can use other storage (e.g. file)
 */
public interface SqlDBRepository {

    /**
     * finds a account in a storage and returns it. Returns  if account is not found
     *
     * @param name name of account
     * @return account or {@code null} if account is not found
     * @throws SQLException if any SQL exception occurs
     */
    Account findByName(String name) throws SQLException;

    /**
     * change last name of account with given name
     *
     * @param name        name of account
     * @param newLastName new last name
     * @throws SQLException if any SQL exception occurs
     */
    void changeLastName(String name, String newLastName) throws SQLException;

}
