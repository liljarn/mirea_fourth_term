package ru.mirea.service.building;

import ru.mirea.dto.AddBuildingRequest;
import ru.mirea.dto.BuildingResponse;
import ru.mirea.dto.RemoveBuildingRequest;

import java.util.List;

public interface BuildingService {
    BuildingResponse addBuilding(AddBuildingRequest request);

    BuildingResponse removeBuilding(RemoveBuildingRequest request);

    List<BuildingResponse> getBuildings();

    List<BuildingResponse> getBuildingsFiltered(String filteredBy, String value);

    BuildingResponse getBuildingById(Long id);
}
