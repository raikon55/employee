package org.raikon.employee.config.grpc;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.raikon.grpc.address.AddressControllerGrpc;
import org.raikon.grpc.address.AddressGrpcRequest;
import org.raikon.grpc.address.AddressGrpcResponse;
import org.springframework.stereotype.Component;

@Component
public class GrpcClient {

    public AddressGrpcResponse clientRequest() {
        final ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8087)
                .usePlaintext()
                .build();

        final AddressGrpcRequest addressGrpcRequest = AddressGrpcRequest.newBuilder()
                .setNumber(1)
                .setZipcode("31995000")
                .build();

        final AddressGrpcResponse addressGrpcResponse =
                AddressControllerGrpc.newBlockingStub(managedChannel).create(addressGrpcRequest);
        managedChannel.shutdown();

        return addressGrpcResponse;
    }
}
