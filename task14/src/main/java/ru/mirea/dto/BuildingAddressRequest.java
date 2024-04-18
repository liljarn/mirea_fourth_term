package ru.mirea.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuildingAddressRequest(@JsonProperty("address_id") Long addressId,
                                     @JsonProperty("building_id") Long buildingId) {
}
