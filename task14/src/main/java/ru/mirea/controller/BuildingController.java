package ru.mirea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.dto.AddBuildingRequest;
import ru.mirea.dto.BuildingResponse;
import ru.mirea.dto.RemoveBuildingRequest;
import ru.mirea.service.BuildingService;

import java.util.List;

@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;

    @PostMapping
    public void addBuilding(@RequestBody AddBuildingRequest request) {
        buildingService.addBuilding(request);
    }

    @DeleteMapping
    public void deleteBuilding(@RequestBody RemoveBuildingRequest request) {
        buildingService.removeBuilding(request);
    }

    @GetMapping
    public List<BuildingResponse> getBuildings() {
        return buildingService.getBuildings();
    }
}
