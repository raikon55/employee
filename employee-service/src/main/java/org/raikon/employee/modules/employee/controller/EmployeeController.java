package org.raikon.employee.modules.employee.controller;

import org.raikon.employee.dao.Employee;
import org.raikon.employee.json.employee.request.EmployeeRequest;
import org.raikon.employee.json.employee.response.EmployeeResponse;
import org.raikon.employee.modules.employee.mapper.EmployeeMapper;
import org.raikon.employee.modules.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    protected EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

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


