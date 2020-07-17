package br.com.mundiale.employee.dal.repository;

import br.com.mundiale.employee.dal.model.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

    @Query("FROM Address a " +
           "WHERE a.zipCode = :zipCode")
    Optional<Address> findByZipCode(@Param("zipCode") String zipCode);
}
