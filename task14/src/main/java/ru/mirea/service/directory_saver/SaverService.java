package ru.mirea.service.directory_saver;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.mirea.service.address.AddressService;
import ru.mirea.service.building.BuildingService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@Service
@ManagedResource
@RequiredArgsConstructor
@Log4j2
@EnableScheduling
public class SaverService {
    private final BuildingService buildingService;
    private final AddressService addressService;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @ManagedOperation
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.MINUTES)
    public void save() {
        log.info("Start saving");
        Path pathToDir = Path.of("task14/src/main/resources/task22");
        File dir = new File(pathToDir.toString());
        if (dir.exists()) {
            FileUtils.deleteDirectory(new File(pathToDir.toString()));
        }
        Files.createDirectory(pathToDir);
        Path addressPath = pathToDir.resolve("address");
        Path buildingPath = pathToDir.resolve("building");
        Files.createDirectory(addressPath);
        Files.createDirectory(buildingPath);
        saveAllEntities(addressPath, buildingPath);
    }

    private void saveAllEntities(Path addressPath, Path buildingPath) {
        addressService.getAddresses().forEach(
                addressResponse -> {
                    try {
                        Files.write(
                                addressPath.resolve(addressResponse.id() + ".json"),
                                objectMapper.writeValueAsBytes(addressResponse)
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        buildingService.getBuildings().forEach(
                buildingResponse -> {
                    try {
                        Files.write(
                                buildingPath.resolve(buildingResponse.id() + ".json"),
                                objectMapper.writeValueAsBytes(buildingResponse)
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        log.info("Entities saved");
    }
}
