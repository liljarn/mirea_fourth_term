package ru.mirea.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.mirea.dao.entity.AddressEntity;
import ru.mirea.dao.entity.BuildingEntity;
import ru.mirea.dto.AddAddressRequest;
import ru.mirea.dto.AddressResponse;
import ru.mirea.dto.BuildingResponse;
import ru.mirea.dto.RemoveAddressRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    public AddressResponse addAddress(AddAddressRequest request) {
        AddressEntity address = new AddressEntity(request.addressText(), request.zipCode());
        session.beginTransaction();
        session.persist(address);
        session.getTransaction().commit();
        return address.toResponse();
    }

    public AddressResponse getAddressById(Long id) {
        AddressEntity address = session.get(AddressEntity.class, id);
        if (address == null) {
            throw new RuntimeException("Patient with id " + id + " not found");
        }
        return address.toResponse();
    }

    public AddressResponse removeAddress(RemoveAddressRequest request) {
        AddressEntity address = session.get(AddressEntity.class, request.id());
        if (address == null) {
            throw new RuntimeException("Patient with id " + request.id() + " not found");
        }
        session.beginTransaction();
        session.remove(address);
        session.getTransaction().commit();
        return address.toResponse();
    }

    public List<AddressResponse> getAddresses() {
        return session.createQuery("select addr from AddressEntity addr", AddressEntity.class)
                .getResultList().stream().map(AddressEntity::toResponse).toList();
    }

    public List<AddressResponse> getAddressesFiltered(String filteredBy, String value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(AddressEntity.class);
        var root = criteria.from(AddressEntity.class);
        criteria.select(root).where(
                builder.greaterThan(root.get(filteredBy), value)
        );
        session.beginTransaction();
        var addresses = session.createQuery(criteria)
                .getResultList()
                .stream()
                .map(AddressEntity::toResponse)
                .toList();
        session.getTransaction().commit();
        return addresses;
    }

    public List<BuildingResponse> getBuildings(Long id) {
        AddressEntity address = session.get(AddressEntity.class, id);
        if (address == null) {
            throw new RuntimeException("Patient with id " + id + " not found");
        }
        return address.getBuildings().stream().map(BuildingEntity::toResponse).toList();
    }

    public void addBuilding(Long addressId, Long buildingId) {
        AddressEntity address = session.get(AddressEntity.class, addressId);
        if (address == null) {
            throw new RuntimeException("Patient with id " + addressId + " not found");
        }
        BuildingEntity building = session.get(BuildingEntity.class, buildingId);
        if (building == null) {
            throw new RuntimeException("Patient with id " + buildingId + " not found");
        }
        session.beginTransaction();
        address.getBuildings().add(building);
        building.setAddress(address);
        session.merge(building);
        session.getTransaction().commit();
    }
}
