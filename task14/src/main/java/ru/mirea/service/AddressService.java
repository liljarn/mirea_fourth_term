package ru.mirea.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.mirea.dto.AddAddressRequest;
import ru.mirea.dto.AddressResponse;
import ru.mirea.dto.RemoveAddressRequest;

import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class AddressService {
    public void addAddress(AddAddressRequest request) {
        log.info("ADDRESS ADDED");
    }

    public void removeAddress(RemoveAddressRequest request) {
        log.info("ADDRESS REMOVED");
    }

    public List<AddressResponse> getAddresses() {
        return Collections.EMPTY_LIST;
    }
}
