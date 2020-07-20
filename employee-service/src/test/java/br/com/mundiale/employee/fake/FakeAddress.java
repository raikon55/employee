package br.com.mundiale.employee.fake;

import br.com.mundiale.employee.dal.model.Address;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FakeAddress {

    public static Address generate() {
        Address address = new Address();
        address.setId(1);
        address.setNeighborhood("Lorem");
        address.setNumber((short) 1);
        address.setStreet("Dolor");
        address.setCity("Ipsum");
        address.setState("Amet");
        address.setZipCode("12345678");

        return address;
    }
}
