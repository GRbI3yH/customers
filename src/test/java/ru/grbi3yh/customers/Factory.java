package ru.grbi3yh.customers;

import org.springframework.stereotype.Component;
import ru.grbi3yh.customers.dto.AddressDto;
import ru.grbi3yh.customers.dto.CustomerDto;
import ru.grbi3yh.customers.entity.Sex;

import java.util.Random;

@Component
public class Factory {

    private final Random random = new Random((long) (Math.random()*100));
    private final int BOUND = 999999;

    public CustomerDto getNewCustomer(Long id) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.id = id;
        customerDto.sex = Sex.male;
        customerDto.firstName = "Имя" + random.nextInt(BOUND);
        customerDto.middleName = "Отчество" + random.nextInt(BOUND);
        customerDto.lastName = "Фамилия" + random.nextInt(BOUND);
//        customerDto.registredAddress = getNewAddress(-1l);
//        customerDto.actualAddress = getNewAddress(-2l);
        return customerDto;
    }

    public AddressDto getNewAddress(Long id) {
        AddressDto addressDto = new AddressDto();
        addressDto.id = id;
        addressDto.contry = "Страна" + random.nextInt(BOUND);
        addressDto.city = "Город" + random.nextInt(BOUND);
        addressDto.region = "Регион" + random.nextInt(BOUND);
        addressDto.street = "Улица" + random.nextInt(BOUND);
        addressDto.house = "Дом" + random.nextInt(BOUND);
        addressDto.flat = "Квартира" + random.nextInt(BOUND);
        return addressDto;
    }
}
