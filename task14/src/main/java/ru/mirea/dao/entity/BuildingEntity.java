package ru.mirea.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "building")
@Setter
@Getter
@NoArgsConstructor
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
}
