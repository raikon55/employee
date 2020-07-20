package br.com.mundiale.employee;

import br.com.mundiale.employee.dal.model.Employee;
import br.com.mundiale.employee.fake.FakeEmployee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class Tests {

    static ObjectMapper mapper = new ObjectMapper();
    @Test
    public void testObjectToJson() {
        final var employee = FakeEmployee.generate();

        try {
            System.out.println(mapper.writeValueAsString(employee));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJsonToObject() {
        try {
            Employee employee = mapper.readValue("{\"id\":1,\"name\":\"Tester\",\"cpf\":\"12345678910\",\"role\":\"INTERN\",\"salaryBase\":1000.0,\"address\":{\"id\":1,\"street\":\"Dolor\",\"zipCode\":\"12345678\",\"number\":1,\"neighborhood\":\"Lorem\",\"city\":\"Ipsum\",\"state\":\"Amet\"}}", Employee.class);
            System.out.println(employee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
