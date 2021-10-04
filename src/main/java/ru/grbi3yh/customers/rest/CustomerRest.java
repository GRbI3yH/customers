package ru.grbi3yh.customers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.grbi3yh.customers.dto.AddressDto;
import ru.grbi3yh.customers.dto.CustomerDto;
import ru.grbi3yh.customers.dto.CustomerSearchCriteria;
import ru.grbi3yh.customers.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerRest {

    /*
    a. Создание клиента
    b. Изменение фактического адреса клиента
    c. Поиск клиента по имени и фамилии
     */

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "hello";
    }

    @RequestMapping(value = "/new", method = RequestMethod.PUT)
    public CustomerDto newCustomer(@RequestBody CustomerDto dto) {
        return customerService.newCustomer(dto);
    }

    @RequestMapping(value = "/{idCustomer}/changeActualAddress", method = RequestMethod.PUT)
    public CustomerDto changeActualAddress(long idCustomer, @RequestBody AddressDto dto) {
        return customerService.changeActualAddress(idCustomer, dto);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<CustomerDto> searchCustomer(@RequestBody CustomerSearchCriteria searchCriteria) {
        return customerService.searchCustomer(searchCriteria);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<CustomerDto> searchCustomer(@RequestParam(value = "firstName") String firstName,
                                            @RequestParam(value = "lastName") String lastName) {
        return searchCustomer(new CustomerSearchCriteria(firstName, lastName));
    }

}
