package br.com.mundiale.employee.modules.employee.mapper;

import br.com.mundiale.employee.dal.model.Employee;
import br.com.mundiale.employee.json.employee.request.EmployeeRequest;
import br.com.mundiale.employee.json.employee.response.EmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "employeeRequest.actualRole", target = "role")
    @Mapping(source = "employeeRequest.idAddress", target = "address.id")
    Employee of(EmployeeRequest employeeRequest);


    EmployeeResponse toResponse(Employee employee);
}
