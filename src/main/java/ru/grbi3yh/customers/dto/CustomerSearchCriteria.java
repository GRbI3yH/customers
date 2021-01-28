package ru.grbi3yh.customers.dto;

public class CustomerSearchCriteria {

    public String firstName;
    public String lastName;

    public CustomerSearchCriteria(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerSearchCriteria() {
    }
}
