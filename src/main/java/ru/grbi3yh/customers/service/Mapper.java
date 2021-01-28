package ru.grbi3yh.customers.service;

import org.springframework.stereotype.Component;
import ru.grbi3yh.customers.dto.AddressDto;
import ru.grbi3yh.customers.dto.CustomerDto;
import ru.grbi3yh.customers.entity.Address;
import ru.grbi3yh.customers.entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class Mapper {

    public CustomerDto map(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.id = customer.getId();
        dto.firstName = customer.getFirstName();
        dto.middleName = customer.getMiddleName();
        dto.lastName = customer.getLastName();
        dto.sex = customer.getSex();
        dto.actualAddress =  map(customer.getActualAddress());
        dto.registredAddress =  map(customer.getRegistredAddress());
        return dto;
    }

    public Customer map(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setId(dto.id);
        customer.setFirstName(dto.firstName);
        customer.setMiddleName(dto.middleName);
        customer.setLastName(dto.lastName);
        customer.setSex(dto.sex);
        if (dto.actualAddress != null){
            customer.setActualAddress(map(dto.actualAddress));
        }
        if (dto.registredAddress != null){
            customer.setRegistredAddress(map(dto.registredAddress));
        }
        return customer;
    }

    public Address map(AddressDto dto) {
        if (isNull(dto)) return null;
        Address address = new Address();
        address.setId(dto.id);
        address.setContry(dto.contry);
        address.setRegion(dto.region);
        address.setCity(dto.city);
        address.setStreet(dto.street);
        address.setHouse(dto.house);
        address.setFlat(dto.flat);
        return address;
    }

    public AddressDto map(Address address) {
        if (isNull(address)) return null;
        AddressDto dto = new AddressDto();
        dto.id = address.getId();
        dto.contry = address.getContry();
        dto.region = address.getRegion();
        dto.city = address.getCity();
        dto.street = address.getStreet();
        dto.house = address.getHouse();
        dto.flat = address.getFlat();
        dto.created = address.getCreated();
        dto.modified = address.getModified();
        return dto;
    }

    public List<CustomerDto> map(List<Customer> customers) {
        return customers.stream().map(this::map).collect(Collectors.toList());
    }
}
