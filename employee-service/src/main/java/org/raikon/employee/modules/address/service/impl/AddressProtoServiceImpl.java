package org.raikon.employee.modules.address.service.impl;

import io.grpc.stub.StreamObserver;
import org.raikon.employee.dal.model.Address;
import org.raikon.employee.modules.address.mapper.AddressMapper;
import org.raikon.employee.modules.address.service.AddressService;
import org.raikon.grpc.address.AddressControllerGrpc;
import org.raikon.grpc.address.AddressGrpcRequest;
import org.raikon.grpc.address.AddressGrpcResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressProtoServiceImpl extends AddressControllerGrpc.AddressControllerImplBase {

    @Autowired
    private AddressService addressService;

    @Override
    public void create(AddressGrpcRequest request, StreamObserver<AddressGrpcResponse> responseObserver) {
        final var address = this.addressService.create(AddressMapper.ofGrpc(request));
        responseObserver.onNext(AddressMapper.toGrpcResponse(address));
        responseObserver.onCompleted();
    }
}
