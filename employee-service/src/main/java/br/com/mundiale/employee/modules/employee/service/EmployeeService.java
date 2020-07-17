package br.com.mundiale.employee.modules.employee.service;

import br.com.mundiale.employee.dal.model.Employee;
import javassist.NotFoundException;

public interface EmployeeService {

    Employee getOrCreate(Employee employee);

    Employee update(Long id, Employee employee) throws NotFoundException;

    void delete(Long id);

    Employee getById(Long id) throws NotFoundException;

    Employee getByName(String name) throws NotFoundException;

    Employee getByCpf(String name) throws NotFoundException;
}
