package org.raikon.employee.modules.employee.mapper;

import org.raikon.employee.dal.model.Address;
import org.raikon.employee.dal.model.Employee;
import org.raikon.employee.json.employee.request.EmployeeRequest;
import org.raikon.employee.json.employee.response.EmployeeResponse;
import org.raikon.employee.modules.address.mapper.AddressMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeMapper {

    public static Employee of(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setCpf(employeeRequest.getCpf());
        employee.setName(employeeRequest.getName());
        employee.setRole(employeeRequest.getActualRole());

        Address address = new Address();
        address.setId(employeeRequest.getIdAddress());
        employee.setAddress(address);

        return employee;
    }

    public static EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setCpf(employee.getCpf());
        employeeResponse.setName(employee.getName());
        employeeResponse.setActualRole(employee.getRole());
        employeeResponse.setAddressResponse(AddressMapper.toResponse(employee.getAddress()));

        return employeeResponse;
    }
}
