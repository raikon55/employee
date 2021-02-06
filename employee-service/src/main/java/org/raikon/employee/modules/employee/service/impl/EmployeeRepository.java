package org.raikon.employee.modules.employee.service.impl;

import org.raikon.employee.dao.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(value = "FROM Employee e " +
            "WHERE e.name = :name " +
            "AND e.deletedAt IS NULL")
    Optional<Employee> findByName(@Param("name") String name);

    @Query(value = "FROM Employee e " +
            "WHERE e.cpf = :cpf " +
            "AND e.deletedAt IS NULL")
    Optional<Employee> findByCpf(@Param("cpf") String cpf);
}
