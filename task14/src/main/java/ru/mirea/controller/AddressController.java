package ru.mirea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.dto.AddAddressRequest;
import ru.mirea.dto.AddressResponse;
import ru.mirea.dto.RemoveAddressRequest;
import ru.mirea.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public void addAddress(@RequestBody AddAddressRequest request) {
        addressService.addAddress(request);
    }

    @DeleteMapping
    public void deleteAddress(@RequestBody RemoveAddressRequest request) {
        addressService.removeAddress(request);
    }

    @GetMapping
    public List<AddressResponse> getAddresses() {
        return addressService.getAddresses();
    }
}
