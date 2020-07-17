package br.com.mundiale.employee.json.employee.response;

import br.com.mundiale.employee.json.address.response.AddressResponse;
import br.com.mundiale.employee.json.employee.request.EmployeeRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse extends EmployeeRequest {

    private static final long serialVersionUID = -8417554719562838650L;

    private Long id;
    private AddressResponse addressResponse;
}
