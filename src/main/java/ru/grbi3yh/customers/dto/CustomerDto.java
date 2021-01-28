package ru.grbi3yh.customers.dto;

import ru.grbi3yh.customers.entity.Sex;

public class CustomerDto {

    public Long id;
    public AddressDto registredAddress;
    public AddressDto actualAddress;
    public String firstName;
    public String lastName;
    public String middleName;
    public Sex sex;
}
