package org.raikon.employee.config.grpc;

import io.grpc.ManagedChannelBuilder;
import org.raikon.grpc.address.AddressControllerGrpc;
import org.raikon.grpc.address.AddressGrpcRequest;
import org.springframework.stereotype.Component;

@Component
public class GrpcClient {

    public void clientRequest() {
        final var managedChannel = ManagedChannelBuilder.forAddress("localhost", 8087)
                .usePlaintext()
                .build();

        final var stub =
                AddressControllerGrpc.newBlockingStub(managedChannel);

        final var addressGrpcRequest = AddressGrpcRequest.newBuilder()
                .setNumber(1)
                .setZipcode("31995000")
                .build();
        final var addressGrpcResponse = stub.create(addressGrpcRequest);
        System.out.println(addressGrpcResponse);
        managedChannel.shutdown();
    }
}
