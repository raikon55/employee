package org.raikon.employee.fake;

import org.raikon.employee.dal.model.Employee;
import org.raikon.employee.enums.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FakeEmployee {

    public static Employee generate() {
        final var employee = new Employee();
        employee.setId(1L);
        employee.setName("Lorem Ipsum");
        employee.setRole(Role.SENIOR);
        employee.setCpf("12345678910");
        employee.setAddress(List.of(FakeAddress.generate()));

        return employee;
    }
}
