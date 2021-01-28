package ru.grbi3yh.customers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.grbi3yh.customers.dto.AddressDto;
import ru.grbi3yh.customers.dto.CustomerDto;
import ru.grbi3yh.customers.dto.CustomerSearchCriteria;
import ru.grbi3yh.customers.entity.Customer;
import ru.grbi3yh.customers.repository.AddressRepository;
import ru.grbi3yh.customers.repository.CustomerRepository;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@Transactional
public class CustomerService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public CustomerDto newCustomer(CustomerDto dto) {
        Customer customer = mapper.map(dto);

        if (nonNull(customer.getActualAddress())) {
            addressRepository.save(customer.getActualAddress());
        }
        if (nonNull(customer.getRegistredAddress())) {
            addressRepository.save(customer.getRegistredAddress());
        }

        return mapper.map(customerRepository.save(customer));
    }

    public CustomerDto changeActualAddress(long idCustomer, AddressDto addressDto) {
        Customer customer = customerRepository.getById(idCustomer);
        if (nonNull(customer)) {
            customer.setActualAddress(addressRepository.save(mapper.map(addressDto)));
            customerRepository.save(customer);
        }
        return mapper.map(customer);
    }

    public List<CustomerDto> searchCustomer(CustomerSearchCriteria searchCriteria) {
        return mapper.map(customerRepository.getCustomersBy(searchCriteria));
    }
}
