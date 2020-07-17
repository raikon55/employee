package br.com.mundiale.employee.modules.address.service.impl;

import br.com.mundiale.employee.dal.model.Address;
import br.com.mundiale.employee.dal.repository.AddressRepository;
import br.com.mundiale.employee.modules.address.service.AddressService;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

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
    public Address getByZipCode(String zipCode) {
        return this.addressRepository.findByZipCode(zipCode)
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
