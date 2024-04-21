package ru.mirea.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mirea.dto.AddBuildingRequest;
import ru.mirea.dto.BuildingResponse;

import java.time.OffsetDateTime;

@Entity
@Table(name = "building")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingEntity {
    public BuildingEntity(String type) {
        this.type = type;
    }

    @Id
    @Column(name = "building_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buildingId;

    @Column(name = "create_time")
    private OffsetDateTime createTime = OffsetDateTime.now();

    @Column(nullable = false)
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    public BuildingResponse toResponse() {
        return new BuildingResponse(buildingId, createTime, type);
    }
}
