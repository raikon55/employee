package br.com.mundiale.employee.modules.employee.controller;

import br.com.mundiale.employee.dal.model.Employee;
import br.com.mundiale.employee.json.employee.request.EmployeeRequest;
import br.com.mundiale.employee.json.employee.response.EmployeeResponse;
import br.com.mundiale.employee.modules.employee.mapper.EmployeeMapper;
import br.com.mundiale.employee.modules.employee.service.EmployeeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @PostMapping
    public EmployeeResponse post(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = this.employeeMapper.of(employeeRequest);
        return this.employeeMapper.toResponse(this.employeeService.getOrCreate(employee));
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable("id") Long id) throws NotFoundException {
        return this.employeeMapper.toResponse(this.employeeService.getById(id));
    }

    @GetMapping("/name/{name}")
    public EmployeeResponse findByName(@PathVariable("name") String name) throws NotFoundException {
        return this.employeeMapper.toResponse(this.employeeService.getByName(name));
    }

    @GetMapping("/cpf/{cpf}")
    public EmployeeResponse findByCpf(@PathVariable("cpf") String cpf) throws NotFoundException {
        return this.employeeMapper.toResponse(this.employeeService.getByCpf(cpf));
    }

    @PutMapping("/{id}")
    public EmployeeResponse put(@PathVariable("id") Long id, @RequestBody EmployeeRequest employeeRequest) throws NotFoundException {
        try {
            return this.employeeMapper.toResponse(this.employeeService.update(id, this.employeeMapper.of(employeeRequest)));
        } catch (NotFoundException exc) {
            throw new NotFoundException("Employee not found!");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.employeeService.delete(id);
    }
}

