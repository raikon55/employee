package org.raikon.employee.modules.address.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.raikon.employee.dao.Address;
import org.raikon.employee.json.address.request.AddressRequest;
import org.raikon.employee.json.address.response.AddressResponse;
import org.raikon.employee.modules.address.mapper.AddressMapper;
import org.raikon.employee.modules.address.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    protected AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public AddressResponse create(@RequestBody AddressRequest addressRequest) {
        Address address = this.addressService.create(AddressMapper.of(addressRequest));
        return AddressMapper.toResponse(address);
    }

    @GetMapping("/{id}")
    public AddressResponse get(@PathVariable("id") Integer id) {
        return AddressMapper.toResponse(this.addressService.getById(id));
    }

    @GetMapping("/zipcode/{zipCode}")
    public List<AddressResponse> getByZipCode(@PathVariable("zipCode") String zipCode) {
        return this.addressService.getByZipCode(zipCode).parallelStream()
                .map(AddressMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public AddressResponse update(@PathVariable("id") Integer id, @RequestBody AddressRequest addressUpdate) {
        return AddressMapper.toResponse(this.addressService.update(id, AddressMapper.of(addressUpdate)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse delete(@PathVariable("id") Integer id) {
        return AddressMapper.toResponse(this.addressService.delete(id));
    }
}
