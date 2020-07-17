package br.com.mundiale.employee.modules.address.mapper;

import br.com.mundiale.employee.dal.model.Address;
import br.com.mundiale.employee.json.address.request.AddressRequest;
import br.com.mundiale.employee.json.address.response.AddressResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMapper {

    public static Address of(AddressRequest addressRequest) {
        final var address = new Address();
        address.setNumber(addressRequest.getNumber());
        address.setStreet(addressRequest.getStreet());
        address.setZipCode(addressRequest.getZipCode());
        address.setState(addressRequest.getState());
        address.setCity(addressRequest.getCity());
        address.setNeighborhood(addressRequest.getNeighborhood());

        return address;
    }

    public static AddressResponse toResponse(Address address) {
        final var addressResponse = new AddressResponse();
        addressResponse.setId(address.getId());
        addressResponse.setNumber(address.getNumber());
        addressResponse.setStreet(address.getStreet());
        addressResponse.setZipCode(address.getZipCode());
        addressResponse.setState(address.getState());
        addressResponse.setCity(address.getCity());
        addressResponse.setNeighborhood(address.getNeighborhood());

        return addressResponse;
    }
}
