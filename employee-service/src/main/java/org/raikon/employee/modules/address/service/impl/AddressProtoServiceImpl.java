package org.raikon.employee.modules.address.service.impl;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.raikon.employee.dal.model.Address;
import org.raikon.employee.modules.address.mapper.AddressMapper;
import org.raikon.employee.modules.address.service.AddressService;
import org.raikon.grpc.address.AddressControllerGrpc;
import org.raikon.grpc.address.AddressGrpcRequest;
import org.raikon.grpc.address.AddressGrpcResponse;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class AddressProtoServiceImpl extends AddressControllerGrpc.AddressControllerImplBase {

    @Autowired
    private AddressService addressService;

    @Override
    public void create(AddressGrpcRequest request, StreamObserver<AddressGrpcResponse> responseObserver) {
        final Address address = this.addressService.create(AddressMapper.ofGrpc(request));
        final AddressGrpcResponse addressGrpcResponse = AddressMapper.toGrpcResponse(address);
        responseObserver.onNext(addressGrpcResponse);
        responseObserver.onCompleted();
    }
}
