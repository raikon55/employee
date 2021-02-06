package org.raikon.employee.modules.employee.service.impl;

import org.raikon.employee.dao.Employee;
import org.raikon.employee.modules.employee.service.EmployeeService;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    protected EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getOrCreate(Employee employee) {
        return this.employeeRepository.findByCpf(employee.getCpf())
                .orElseGet(() -> this.employeeRepository.save(employee));
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
