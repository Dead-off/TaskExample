package org.example;

public class Account {

    private final String name;
    private final String lastName;

    public Account(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
}
