package org.raikon.employee.modules.address.service;

import org.raikon.employee.dal.model.Address;

public interface AddressService {

    Address create(Address address);

    Address getById(Integer id);

    Address getByZipCode(String zipCode);

    Address update(Integer id, Address updatedAddress);

    void delete(Integer id);
}
