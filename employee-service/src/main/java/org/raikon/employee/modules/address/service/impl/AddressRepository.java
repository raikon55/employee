package org.raikon.employee.modules.address.service.impl;

import org.raikon.employee.dao.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

    @Query("FROM Address a " +
           "WHERE a.zipCode = :zipCode")
    List<Address> findByZipCode(@Param("zipCode") String zipCode);
}
