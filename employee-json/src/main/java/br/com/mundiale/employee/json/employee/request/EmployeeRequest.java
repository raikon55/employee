package br.com.mundiale.employee.json.employee.request;

import br.com.mundiale.employee.enums.Role;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class EmployeeRequest implements Serializable {

    private static final long serialVersionUID = -4098635025216225362L;

    @Pattern(regexp = "[\\D\\s]+")
    private String name;
    @Pattern(regexp = "[\\d]{11}")
    private String cpf;
    @Pattern(regexp = "(INTERN\\|JUNIOR\\|PLAN\\|SENIOR)")
    private Role actualRole;
    private Integer idAddress;
}
