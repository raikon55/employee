package org.raikon.employee.tests;

import org.raikon.employee.dal.model.Address;
import org.raikon.employee.fake.FakeAddress;
import org.junit.jupiter.api.Test;
import org.springframework.boot.json.JacksonJsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ParallelTest {

    @Test
    public void test() {
        Address address = FakeAddress.generate();
        address.setZipCode("31995000");

        long start = System.currentTimeMillis();
        try {
            CompletableFuture.supplyAsync(() -> validateZipCode(address)).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(address);

        address.setZipCode("30451058");
        long start1 = System.currentTimeMillis();
        validateZipCode(address);
        System.out.println(System.currentTimeMillis() - start1);
        System.out.println(address);
    }

    private Address validateZipCode(Address address) {

        HttpRequest request = HttpRequest
                .newBuilder(URI.create("http://viacep.com.br/ws/" + address.getZipCode() + "/json/")).build();

        HttpClient.newHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenApply(HttpResponse::body)
                .thenAccept(jsonResponse -> {
                    Map<String, Object> jsonMap = new JacksonJsonParser().parseMap(jsonResponse);
                    address.setStreet(jsonMap.get("logradouro").toString());
                    address.setNeighborhood(jsonMap.get("bairro").toString());
                    address.setCity(jsonMap.get("localidade").toString());
                    address.setState(jsonMap.get("uf").toString());
                })
                .join();

        return address;
    }
}
