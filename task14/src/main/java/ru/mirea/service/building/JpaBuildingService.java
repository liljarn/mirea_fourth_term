package ru.mirea.service.building;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.dao.entity.BuildingEntity;
import ru.mirea.dao.repository.BuildingRepository;
import ru.mirea.dto.AddBuildingRequest;
import ru.mirea.dto.BuildingResponse;
import ru.mirea.dto.RemoveBuildingRequest;
import ru.mirea.service.email.EmailService;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Log4j2
public class JpaBuildingService implements BuildingService {
    private final BuildingRepository repository;
    private final EmailService emailService;

    @Override
    public BuildingResponse addBuilding(AddBuildingRequest request) {
        BuildingEntity building = new BuildingEntity(request.type());
        repository.save(building);
        emailService.send(building.toResponse().toString());
        log.info("Building was saved: {}", building.toResponse());
        return building.toResponse();
    }

    @Override
    public BuildingResponse removeBuilding(RemoveBuildingRequest request) {
        BuildingEntity building = repository.findById(request.id()).orElseThrow(RuntimeException::new);
        repository.deleteById(request.id());
        log.info("Building was removed: {}", building.toResponse());
        return building.toResponse();
    }

    @Override
    public List<BuildingResponse> getBuildings() {
        log.info("Buildings were gotten");
        return repository.findAll().stream().map(BuildingEntity::toResponse).toList();
    }

    @Override
    public List<BuildingResponse> getBuildingsFiltered(String filteredBy, String value) {
        log.info("Buildings were gotten FILTERED");
        if (filteredBy.equals("type")) {
            return repository.findBuildingEntitiesByTypeGreaterThan(value)
                    .stream().map(BuildingEntity::toResponse).toList();
        }
        throw new IllegalArgumentException();
    }

    @Override
    public BuildingResponse getBuildingById(Long id) {
        BuildingEntity entity = repository.findById(id).orElseThrow(RuntimeException::new);
        log.info("Got building, {}", entity.toResponse());
        return entity.toResponse();
    }
}
