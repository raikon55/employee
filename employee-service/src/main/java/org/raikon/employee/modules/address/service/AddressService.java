package org.raikon.employee.modules.address.service;

import org.raikon.employee.dao.Address;

import java.util.List;

public interface AddressService {

    Address create(Address address);

    Address getById(Integer id);

    List<Address> getByZipCode(String zipCode);

    Address update(Integer id, Address updatedAddress);

    Address delete(Integer id);
}
