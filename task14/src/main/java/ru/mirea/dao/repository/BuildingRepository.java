package ru.mirea.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.dao.entity.BuildingEntity;
import ru.mirea.dto.AddAddressRequest;
import ru.mirea.dto.AddressResponse;

import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
    List<BuildingEntity> findBuildingEntitiesByTypeGreaterThan(String type);
}
