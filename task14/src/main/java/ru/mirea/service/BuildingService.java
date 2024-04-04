package ru.mirea.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.mirea.dto.AddBuildingRequest;
import ru.mirea.dto.BuildingResponse;
import ru.mirea.dto.RemoveBuildingRequest;

import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class BuildingService {
    public void addBuilding(AddBuildingRequest request) {
        log.info("BUILDING ADDED");
    }

    public void removeBuilding(RemoveBuildingRequest request) {
        log.info("BUILDING REMOVED");
    }

    public List<BuildingResponse> getBuildings() {
        return Collections.EMPTY_LIST;
    }
}
