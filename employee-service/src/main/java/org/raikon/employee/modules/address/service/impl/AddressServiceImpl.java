package org.raikon.employee.modules.address.service.impl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.raikon.employee.dao.Address;
import org.raikon.employee.modules.address.service.AddressService;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.SneakyThrows;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    protected AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address create(Address address) {
        Address fullAddress = this.validateZipCode(address);
        return this.addressRepository.save(fullAddress);
    }

    @SneakyThrows
    public Address getById(Integer id) {
        return this.addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found!"));
    }

    @SneakyThrows
    public List<Address> getByZipCode(String zipCode) {
        return this.addressRepository.findByZipCode(zipCode);
    }

    @SneakyThrows
    public Address update(Integer id, Address updatedAddress) {
        final var address = this.validateZipCode(updatedAddress);
        return this.addressRepository.findById(id)
                .map(address1 -> {
                    address.setId(address1.getId());
                    return this.addressRepository.save(address);
                })
                .orElseThrow(() -> new NotFoundException("Address not found!"));
    }

    @SneakyThrows
    public Address delete(Integer id) {
        return this.addressRepository.findById(id)
                .map(address -> {
                    address.setDeletedAt(LocalDateTime.now());
                    return this.addressRepository.save(address);
                })
                .orElseThrow(() -> new NotFoundException("Address not found!"));
    }

    private Address validateZipCode(Address address) {

        HttpRequest request = HttpRequest.newBuilder(URI.create("http://viacep.com.br/ws/" + address.getZipCode() + "/json/"))
                .build();

        HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
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
