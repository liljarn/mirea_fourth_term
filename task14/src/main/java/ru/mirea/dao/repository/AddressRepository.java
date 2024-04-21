package ru.mirea.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.dao.entity.AddressEntity;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findAddressEntitiesByAddressIdGreaterThan(Long id);
    List<AddressEntity> findAddressEntitiesByAddressTextGreaterThan(String text);
    List<AddressEntity> findAddressEntitiesByZipCodeGreaterThan(Long code);
}
