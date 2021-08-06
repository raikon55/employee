package org.raikon.employee.modules.address.service.impl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Override
    @SneakyThrows
    public Address create(Address address) {
        return this.validateZipCode(address.getZipCode())
                .map(this.addressRepository::save)
                .orElseThrow(() -> new NotFoundException("Can't retrieve an address from this zip code " + address.getZipCode()));
    }

    @Override
    @SneakyThrows
    public Address getById(Integer id) {
        return this.addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found!"));
    }

    @Override
    @SneakyThrows
    public List<Address> getByZipCode(String zipCode) {
        return this.addressRepository.findByZipCode(zipCode);
    }

    @Override
    @SneakyThrows
    public Address update(Integer id, Address updatedAddress) {
        return this.addressRepository.findById(id)
                .map(addressToUpdate -> {
                    final var address = this.validateZipCode(updatedAddress.getZipCode()).orElse(addressToUpdate);
                    address.setId(addressToUpdate.getId());
                    return address;
                })
                .map(this.addressRepository::save)
                .orElseThrow(() -> new NotFoundException("Address not found!"));
    }

    @Override
    @SneakyThrows
    public Address delete(Integer id) {
        return this.addressRepository.findById(id)
                .map(address -> {
                    address.setDeletedAt(LocalDateTime.now());
                    return this.addressRepository.save(address);
                })
                .orElseThrow(() -> new NotFoundException("Address not found!"));
    }

    private Optional<Address> validateZipCode(String zipCode) {

        HttpRequest request = HttpRequest.newBuilder(URI.create("http://viacep.com.br/ws/" + zipCode + "/json/"))
                .build();

        return Optional.ofNullable(
                HttpClient.newHttpClient()
                        .sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                        .thenApplyAsync(HttpResponse::body)
                        .thenApplyAsync(this::buildAddressFromJson)
                        .join()
                );
    }

    private Address buildAddressFromJson(String jsonResponse) {
        final var address = new Address();
        Map<String, Object> jsonMap = new JacksonJsonParser().parseMap(jsonResponse);

        address.setStreet(jsonMap.get("logradouro").toString());
        address.setNeighborhood(jsonMap.get("bairro").toString());
        address.setCity(jsonMap.get("localidade").toString());
        address.setState(jsonMap.get("uf").toString());

        return address;
    }
}
