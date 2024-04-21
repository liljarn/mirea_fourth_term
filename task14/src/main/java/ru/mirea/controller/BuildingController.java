package ru.mirea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mirea.dto.AddBuildingRequest;
import ru.mirea.dto.BuildingResponse;
import ru.mirea.dto.RemoveBuildingRequest;
import ru.mirea.service.building.BuildingService;

import java.util.List;

@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController {
    private final BuildingService buildingService;

    @PostMapping
    public BuildingResponse addBuilding(@RequestBody AddBuildingRequest request) {
        return buildingService.addBuilding(request);
    }

    @DeleteMapping
    public BuildingResponse deleteBuilding(@RequestBody RemoveBuildingRequest request) {
        return buildingService.removeBuilding(request);
    }

    @GetMapping
    public List<BuildingResponse> getBuildings() {
        return buildingService.getBuildings();
    }

    @GetMapping("/filtered")
    public List<BuildingResponse> getBuildingsFiltered(@RequestParam String filteredBy, @RequestParam String value) {
        return buildingService.getBuildingsFiltered(filteredBy, value);
    }

    @GetMapping("/{id}")
    public BuildingResponse getBuildingById(@PathVariable Long id) {
        return buildingService.getBuildingById(id);
    }
}
