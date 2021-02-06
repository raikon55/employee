package org.raikon.employee.modules.address.service.impl;

import org.raikon.employee.dao.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

    @Override
    @Query("FROM Address a " +
           "WHERE a.id = :id " +
           "AND a.deletedAt IS NULL")
    Optional<Address> findById(@Param("id") Integer id);

    @Query("FROM Address a " +
           "WHERE a.zipCode = :zipCode " +
           "AND a.deletedAt IS NULL")
    List<Address> findByZipCode(@Param("zipCode") String zipCode);
}
