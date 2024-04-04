package ru.mirea.dto;

import java.time.OffsetDateTime;

public record BuildingResponse(Long id, OffsetDateTime createTime, Long zipCode) {
}
