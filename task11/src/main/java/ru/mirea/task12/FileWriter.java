package ru.mirea.task12;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class FileWriter {
    private final Hasher hasher;
    private final List<String> args;

    @PostConstruct
    @SneakyThrows
    private void init() {
        log.info("START HASHING");
        Path path = Path.of(args.get(0));
        Path hashPath = Path.of(args.get(1));
        log.info("REMOVING OLD HASH");
        Files.deleteIfExists(hashPath);
        log.info("CREATING NEW HASH");
        Files.createFile(hashPath);
        if (!Files.exists(path)) {
            Files.write(hashPath, "null".getBytes());
        }
        String text = hasher.getHash(Files.readAllBytes(path));
        Files.write(hashPath, text.getBytes());
    }

    @PreDestroy
    @SneakyThrows
    private void destroy() {
        Path path = Path.of(args.get(0));
        Files.deleteIfExists(path);
    }
}
