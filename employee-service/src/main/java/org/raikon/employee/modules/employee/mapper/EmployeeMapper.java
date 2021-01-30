package org.raikon.employee.modules.employee.mapper;

import org.raikon.employee.dao.Address;
import org.raikon.employee.dao.Employee;
import org.raikon.employee.json.employee.request.EmployeeRequest;
import org.raikon.employee.json.employee.response.EmployeeResponse;
import org.raikon.employee.modules.address.mapper.AddressMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeMapper {

    public static Employee of(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setCpf(employeeRequest.getCpf());
        employee.setName(employeeRequest.getName());
        employee.setRole(employeeRequest.getActualRole());

        Address address = new Address();
        address.setId(employeeRequest.getIdAddress());
        employee.setAddress(List.of(address));

        return employee;
    }

    public static EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setCpf(employee.getCpf());
        employeeResponse.setName(employee.getName());
        employeeResponse.setActualRole(employee.getRole());
        employeeResponse.setAddressResponse(employee.getAddress()
                .stream()
                .map(AddressMapper::toResponse)
                .collect(Collectors.toList()));

        return employeeResponse;
    }
}
