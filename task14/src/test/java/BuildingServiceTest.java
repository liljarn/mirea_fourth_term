import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mirea.dao.entity.BuildingEntity;
import ru.mirea.dao.repository.BuildingRepository;
import ru.mirea.dto.AddBuildingRequest;
import ru.mirea.dto.RemoveBuildingRequest;
import ru.mirea.service.building.JpaBuildingService;
import ru.mirea.service.email.EmailService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public class BuildingServiceTest {
    private static EmailService emailService;
    private static BuildingRepository buildingRepository;
    private static List<BuildingEntity> entities;

    @BeforeAll
    public static void init() {
        emailService = Mockito.mock(EmailService.class);
        buildingRepository = Mockito.mock(BuildingRepository.class);
        entities = List.of(
                new BuildingEntity(1L, OffsetDateTime.now(), "Hruschevka", null),
                new BuildingEntity(2L, OffsetDateTime.now(), "Scyscraper", null),
                new BuildingEntity(3L, OffsetDateTime.now(), "Domik", null)
        );
        Mockito.when(buildingRepository.findAll()).thenReturn(entities);
    }

    @Test
    @DisplayName("Тестирование JpaBuildingService#getBuildings")
    public void getAllBuildingsShouldReturnAllBuildings() {
        JpaBuildingService buildingService = new JpaBuildingService(
                buildingRepository,
                emailService
        );
        Assertions.assertThat(
                buildingService.getBuildings()
        ).containsAll(entities.stream().map(BuildingEntity::toResponse).toList());
    }

    @Test
    @DisplayName("Тестирование JpaBuildingService#getById")
    public void getByIdShouldReturnBuilding() {
        Mockito.when(buildingRepository.findById(1L)).thenReturn(Optional.ofNullable(entities.get(1)));
        JpaBuildingService buildingService = new JpaBuildingService(
                buildingRepository,
                emailService
        );
        Assertions.assertThat(
                buildingService.getBuildingById(1L).type()
        ).isEqualTo(entities.get(1).getType());
    }

    @Test
    @DisplayName("Тестирование JpaBuildingService#addBuilding")
    public void addBuildingShouldReturnRegisteredBuilding() {
        JpaBuildingService buildingService = new JpaBuildingService(
                buildingRepository,
                emailService
        );
        buildingService.addBuilding(new AddBuildingRequest("Shalash"));
        Mockito.verify(buildingRepository).save(Mockito.any(BuildingEntity.class));
    }

    @Test
    @DisplayName("Тестирование JpaBuildingService#removeBuildingById")
    public void removeBuildingByIdShouldRemoveBuildings() {
        JpaBuildingService buildingService = new JpaBuildingService(
                buildingRepository,
                emailService
        );
        Mockito.when(buildingRepository.findById(1L)).thenReturn(Optional.ofNullable(entities.get(1)));
        RemoveBuildingRequest request = new RemoveBuildingRequest(1L);
        buildingService.removeBuilding(request);
        Mockito.verify(buildingRepository).deleteById(1L);
    }
}
