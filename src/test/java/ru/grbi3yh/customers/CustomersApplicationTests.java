package ru.grbi3yh.customers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.grbi3yh.customers.entity.Customer;
import ru.grbi3yh.customers.repository.AddressRepository;
import ru.grbi3yh.customers.repository.CustomerRepository;
import ru.grbi3yh.customers.rest.CustomerRest;
import ru.grbi3yh.customers.dto.AddressDto;
import ru.grbi3yh.customers.dto.CustomerDto;

import java.util.List;

@SpringBootTest
@Transactional
class CustomersApplicationTests {

	@Autowired
	private CustomerRest customerRest;

	@Autowired
	private Factory factory;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Test
	void testNewCustomer() {
//		Добавление
		CustomerDto customer = factory.getNewCustomer(-1l);
		customer.actualAddress = factory.getNewAddress(-1l);
		customer.registredAddress = factory.getNewAddress(-2l);
//		Проверка
		customerRest.newCustomer(customer);
		Assert.notNull(customerRepository.getById(-1),"Новый клиент не найден");
//		Очистка
		customerRepository.remove(-1);
		addressRepository.remove(-1);
		addressRepository.remove(-2);
	}

	@Test
	void testChangeAddress() throws Exception {
//		Добавление
		CustomerDto customer = factory.getNewCustomer(-1l);
		customer.actualAddress = factory.getNewAddress(-1l);
		customer.registredAddress = factory.getNewAddress(-2l);
		AddressDto dto = factory.getNewAddress(-3l);
		customerRest.newCustomer(customer);
//		Проверка

		customerRest.changeActualAddress(customer.id, dto);
		Customer customer1 = customerRepository.getById(-1);
		Assert.isTrue(
				!customer1.getActualAddress().getCity().equals(customer.registredAddress.city)
				&& !customer1.getActualAddress().getStreet().equals(customer.registredAddress.street),
				"Адрес не обновлен");

//		Очистка
		customerRepository.remove(-1);
		addressRepository.remove(-1);
		addressRepository.remove(-2);
		addressRepository.remove(-3);
	}

	@Test
	void testSearchCustomer() throws Exception {
//		Добавление
		CustomerDto customer1 = factory.getNewCustomer(-1l);
		CustomerDto customer2 = factory.getNewCustomer(-2l);
		AddressDto address1 = factory.getNewAddress(-1l);
		AddressDto address2 = factory.getNewAddress(-2l);
		customer1.registredAddress = address1;
		customer1.actualAddress = address1;
		customer2.registredAddress = address2;
		customer2.actualAddress = address2;


		customerRest.newCustomer(customer1);
		customerRest.newCustomer(customer2);
		customer1.id = -3l;
		customerRest.newCustomer(customer1);


//		Проверка
		List<CustomerDto> customers = customerRest.searchCustomer(customer1.firstName, customer1.lastName);
		Assert.isTrue(customers.size() == 2,"Не верное количество элементов");
//		Очистка
		customerRepository.remove(-1);
		customerRepository.remove(-2);
		customerRepository.remove(-3);
		addressRepository.remove(-1);
		addressRepository.remove(-2);
	}
}
