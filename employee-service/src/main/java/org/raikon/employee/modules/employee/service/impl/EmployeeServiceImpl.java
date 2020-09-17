package org.raikon.employee.modules.employee.service.impl;

import org.raikon.employee.dal.model.Employee;
import org.raikon.employee.dal.repository.EmployeeRepository;
import org.raikon.employee.modules.address.service.AddressService;
import org.raikon.employee.modules.employee.service.EmployeeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.IntFunction;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private AddressService addressService;

    @Override
    public Employee getOrCreate(Employee employee) {
        return this.repository.findByCpf(employee.getCpf())
                .orElseGet(() -> this.repository.save(employee));
    }

    @Override
    public Employee update(Long id, Employee employee) throws NotFoundException {
        if (id.equals(employee.getId())) {
            throw new IllegalArgumentException("IDs not equals!");
        }

        final var newEmployee = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee's ID not found!"));
        newEmployee.setRole(employee.getRole());
        newEmployee.setName(employee.getName());
        newEmployee.setCpf(employee.getCpf());
        newEmployee.setAddress(employee.getAddress());
        return this.repository.save(newEmployee);
    }

    @Override
    public void delete(Long id) {
        try {
            this.repository.deleteById(id);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Employee not found!");
        }
    }

    @Override
    public Employee getById(Long id) throws NotFoundException {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found!"));
    }

    @Override
    public Employee getByName(String name) throws NotFoundException {
        return this.repository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Employee not found!"));
    }

    @Override
    public Employee getByCpf(String cpf) throws NotFoundException {
        return this.repository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Employee not found!"));
    }
}
