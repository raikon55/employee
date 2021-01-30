package org.raikon.employee.modules.employee.service.impl;

import java.util.function.IntFunction;

import org.raikon.employee.dao.Employee;
import org.raikon.employee.modules.address.service.AddressService;
import org.raikon.employee.modules.employee.service.EmployeeService;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressService addressService;

    protected EmployeeServiceImpl(EmployeeRepository employeeRepository,
                                  AddressService addressService) {
        this.employeeRepository = employeeRepository;
        this.addressService = addressService;
    }

    @Override
    public Employee getOrCreate(Employee employee) {
        IntFunction<Employee> validateAddress = idAddress -> {
            employee.setAddress(this.addressService.getById(idAddress));
            return employee;
        };

        return this.employeeRepository.findByCpf(
            validateAddress.apply(employee.getAddress().getId()).getCpf()
        ).orElseGet(() -> this.employeeRepository.save(employee));
    }

    @Override
    public Employee update(Long id, Employee employee) throws NotFoundException {
        if (id.equals(employee.getId())) {
            throw new IllegalArgumentException("IDs not equals!");
        }

        final var newEmployee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee's ID not found!"));
        newEmployee.setRole(employee.getRole());
        newEmployee.setName(employee.getName());
        newEmployee.setCpf(employee.getCpf());
        newEmployee.setAddress(employee.getAddress());
        return this.employeeRepository.save(newEmployee);
    }

    @Override
    public void delete(Long id) {
        try {
            this.employeeRepository.deleteById(id);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Employee not found!");
        }
    }

    @Override
    public Employee getById(Long id) throws NotFoundException {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found!"));
    }

    @Override
    public Employee getByName(String name) throws NotFoundException {
        return this.employeeRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Employee not found!"));
    }

    @Override
    public Employee getByCpf(String cpf) throws NotFoundException {
        return this.employeeRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("Employee not found!"));
    }
}
