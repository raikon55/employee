package br.com.mundiale.employee.fake;

import br.com.mundiale.employee.dal.model.Employee;
import br.com.mundiale.employee.enums.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FakeEmployee {

    public static Employee generate() {
        final var employee = new Employee();
        employee.setId(1L);
        employee.setName("Lorem Ipsum");
        employee.setRole(Role.SENIOR);
        employee.setCpf("12345678910");
        employee.setAddress(FakeAddress.generate());

        return employee;
    }
}
