package ru.mirea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.dto.*;
import ru.mirea.service.address.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public AddressResponse addAddress(@RequestBody AddAddressRequest request) {
        return addressService.addAddress(request);
    }

    @DeleteMapping
    public AddressResponse deleteAddress(@RequestBody RemoveAddressRequest request) {
        return addressService.removeAddress(request);
    }

    @GetMapping
    public List<AddressResponse> getAddresses() {
        return addressService.getAddresses();
    }

    @GetMapping("/filtered")
    public List<AddressResponse> getAddressesFiltered(@RequestParam String filteredBy, @RequestParam String value) {
        return addressService.getAddressesFiltered(filteredBy, value);
    }

    @GetMapping("/{id}")
    public AddressResponse getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @GetMapping("/buildings/{id}")
    public List<BuildingResponse> getBuildings(@PathVariable Long id) {
        return addressService.getBuildings(id);
    }

    @PostMapping("/buildings")
    public void addBuilding(@RequestBody BuildingAddressRequest request) {
        addressService.addBuilding(request.addressId(), request.buildingId());
    }
}
