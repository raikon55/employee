package org.raikon.employee.config.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.raikon.employee.modules.address.service.impl.AddressProtoServiceImpl;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GrpcServer {

    public void init() {
        Server server = ServerBuilder.forPort(8087)
                .addService(new AddressProtoServiceImpl())
                .build();

        try {
            server.start();
            server.awaitTermination();
        } catch (IOException | InterruptedException exc) {
            exc.printStackTrace();
        }
    }
}
