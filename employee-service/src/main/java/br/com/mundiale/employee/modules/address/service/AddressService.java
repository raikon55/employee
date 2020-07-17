package br.com.mundiale.employee.modules.address.service;

import br.com.mundiale.employee.dal.model.Address;

public interface AddressService {

    Address create(Address address);

    Address getById(Integer id);

    Address getByZipCode(String zipCode);
}
