package org.raikon.employee.modules.address.mapper;

import org.raikon.employee.dal.model.Address;
import org.raikon.employee.json.address.request.AddressRequest;
import org.raikon.employee.json.address.response.AddressResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.raikon.grpc.address.AddressGrpcRequest;
import org.raikon.grpc.address.AddressGrpcResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMapper {

    public static Address of(AddressRequest addressRequest) {
        final var address = new Address();
        address.setNumber(addressRequest.getNumber());
        address.setZipCode(addressRequest.getZipCode());

        return address;
    }

    public static Address ofGrpc(AddressGrpcRequest addressGrpcRequest) {
        final var address = new Address();
        address.setNumber((short) addressGrpcRequest.getNumber());
        address.setZipCode(addressGrpcRequest.getZipcode());

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

    public static AddressGrpcResponse toGrpcResponse(Address address) {
        return AddressGrpcResponse.newBuilder()
                .setId(address.getId())
                .setCity(address.getCity())
                .setState(address.getState())
                .setNumber(address.getNumber())
                .setStreet(address.getStreet())
                .setZipcode(address.getZipCode())
                .setNeighborhood(address.getNeighborhood())
                .build();
    }
}
