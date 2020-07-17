package br.com.mundiale.employee.modules.address.controller;

import br.com.mundiale.employee.dal.model.Address;
import br.com.mundiale.employee.json.address.request.AddressRequest;
import br.com.mundiale.employee.json.address.response.AddressResponse;
import br.com.mundiale.employee.modules.address.mapper.AddressMapper;
import br.com.mundiale.employee.modules.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

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
    public AddressResponse getByZipCode(@PathVariable("zipCode") String zipCode) {
        return AddressMapper.toResponse(this.addressService.getByZipCode(zipCode));
    }
}
