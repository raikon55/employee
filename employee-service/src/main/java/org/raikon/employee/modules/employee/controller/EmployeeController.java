package org.raikon.employee.modules.employee.controller;

import org.raikon.employee.dal.model.Employee;
import org.raikon.employee.json.employee.request.EmployeeRequest;
import org.raikon.employee.json.employee.response.EmployeeResponse;
import org.raikon.employee.modules.employee.mapper.EmployeeMapper;
import org.raikon.employee.modules.employee.service.EmployeeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public EmployeeResponse post(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = EmployeeMapper.of(employeeRequest);
        return EmployeeMapper.toResponse(this.employeeService.getOrCreate(employee));
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable("id") Long id) throws NotFoundException {
        return EmployeeMapper.toResponse(this.employeeService.getById(id));
    }

    @GetMapping("/name/{name}")
    public EmployeeResponse findByName(@PathVariable("name") String name) throws NotFoundException {
        return EmployeeMapper.toResponse(this.employeeService.getByName(name));
    }

    @GetMapping("/cpf/{cpf}")
    public EmployeeResponse findByCpf(@PathVariable("cpf") String cpf) throws NotFoundException {
        return EmployeeMapper.toResponse(this.employeeService.getByCpf(cpf));
    }

    @PutMapping("/{id}")
    public EmployeeResponse put(@PathVariable("id") Long id, @RequestBody EmployeeRequest employeeRequest) throws NotFoundException {
        try {
            return EmployeeMapper.toResponse(this.employeeService.update(id, EmployeeMapper.of(employeeRequest)));
        } catch (NotFoundException exc) {
            throw new NotFoundException("Employee not found!");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.employeeService.delete(id);
    }
}


