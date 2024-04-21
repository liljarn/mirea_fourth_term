package ru.mirea.service.address;

import ru.mirea.dto.AddAddressRequest;
import ru.mirea.dto.AddressResponse;
import ru.mirea.dto.BuildingResponse;
import ru.mirea.dto.RemoveAddressRequest;

import java.util.List;

public interface AddressService {
    AddressResponse addAddress(AddAddressRequest request);

    AddressResponse getAddressById(Long id);

    AddressResponse removeAddress(RemoveAddressRequest request);

    List<AddressResponse> getAddresses();

    List<AddressResponse> getAddressesFiltered(String filteredBy, String value);

    List<BuildingResponse> getBuildings(Long id);

    void addBuilding(Long addressId, Long buildingId);
}
