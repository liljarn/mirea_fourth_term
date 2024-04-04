package ru.mirea.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import ru.mirea.dao.entity.AddressEntity;
import ru.mirea.dto.AddAddressRequest;
import ru.mirea.dto.AddressResponse;
import ru.mirea.dto.RemoveAddressRequest;

import java.util.Collections;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class AddressService {
//    private final Session session;

    public void addAddress(AddAddressRequest request) {
        AddressEntity address = new AddressEntity(request.addressText(), request.zipCode());

        log.info("ADDRESS ADDED");
    }

    public void removeAddress(RemoveAddressRequest request) {
        log.info("ADDRESS REMOVED");
    }

    public List<AddressResponse> getAddresses() {
        return Collections.EMPTY_LIST;
    }
}
