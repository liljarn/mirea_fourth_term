package ru.mirea.service.address;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.dao.entity.AddressEntity;
import ru.mirea.dao.entity.BuildingEntity;
import ru.mirea.dao.repository.AddressRepository;
import ru.mirea.dao.repository.BuildingRepository;
import ru.mirea.dto.AddAddressRequest;
import ru.mirea.dto.AddressResponse;
import ru.mirea.dto.BuildingResponse;
import ru.mirea.dto.RemoveAddressRequest;
import ru.mirea.service.email.EmailService;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Transactional
public class JpaAddressService implements AddressService {
    private final AddressRepository addressRepository;
    private final BuildingRepository buildingRepository;
    private final EmailService emailService;

    @Override
    public AddressResponse addAddress(AddAddressRequest request) {
        AddressEntity address = new AddressEntity(request.addressText(), request.zipCode());
        addressRepository.save(address);
        log.info("Added new address {}", address.toResponse());
        emailService.send(address.toResponse().toString());
        return address.toResponse();
    }

    @Override
    public AddressResponse getAddressById(Long id) {
        AddressEntity address = addressRepository.findById(id).orElseThrow(RuntimeException::new);
        log.info("Got address by id: {}", id);
        return address.toResponse();
    }

    @Override
    public AddressResponse removeAddress(RemoveAddressRequest request) {
        AddressEntity address = addressRepository.findById(request.id()).orElseThrow(RuntimeException::new);
        addressRepository.deleteById(request.id());
        log.info("Removed address {}", address.toResponse());
        return address.toResponse();
    }

    @Override
    public List<AddressResponse> getAddresses() {
        log.info("Got all addresses");
        return addressRepository.findAll().stream().map(AddressEntity::toResponse).toList();
    }

    @Override
    public List<AddressResponse> getAddressesFiltered(String filteredBy, String value) {
        log.info("Got addresses filtered by {}, higher than {}", filteredBy, value);
        return switch (filteredBy) {
            case "id" -> addressRepository.findAddressEntitiesByAddressIdGreaterThan(Long.valueOf(value))
                    .stream().map(AddressEntity::toResponse).toList();
            case "addressText" -> addressRepository.findAddressEntitiesByAddressTextGreaterThan(value)
                    .stream().map(AddressEntity::toResponse).toList();
            case "zipCode" -> addressRepository.findAddressEntitiesByZipCodeGreaterThan(Long.valueOf(value))
                    .stream().map(AddressEntity::toResponse).toList();
            default -> throw new IllegalStateException("Unexpected value: " + filteredBy);
        };
    }

    @Override
    public List<BuildingResponse> getBuildings(Long id) {
        AddressEntity address = addressRepository.findById(id).orElseThrow(RuntimeException::new);
        log.info("Got buildings by id: {}", id);
        return address.getBuildings().stream().map(BuildingEntity::toResponse).toList();
    }

    @Override
    public void addBuilding(Long addressId, Long buildingId) {
        AddressEntity address = addressRepository.findById(addressId).orElseThrow(RuntimeException::new);
        BuildingEntity building = buildingRepository.findById(buildingId).orElseThrow(RuntimeException::new);
        address.getBuildings().add(building);
        log.info("Added building: {}", buildingId);
        building.setAddress(address);
    }
}
