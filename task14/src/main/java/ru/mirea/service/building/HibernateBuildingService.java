package ru.mirea.service.building;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import ru.mirea.dao.entity.BuildingEntity;
import ru.mirea.dto.AddBuildingRequest;
import ru.mirea.dto.BuildingResponse;
import ru.mirea.dto.RemoveBuildingRequest;

import java.util.List;

@RequiredArgsConstructor
public class HibernateBuildingService implements BuildingService {
    private final SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    public BuildingResponse addBuilding(AddBuildingRequest request) {
        BuildingEntity building = new BuildingEntity(request.type());
        session.beginTransaction();
        session.persist(building);
        session.getTransaction().commit();
        return building.toResponse();
    }

    public BuildingResponse removeBuilding(RemoveBuildingRequest request) {
        BuildingEntity building = session.get(BuildingEntity.class, request.id());
        if (building == null) {
            throw new RuntimeException("Patient with id " + request.id() + " not found");
        }
        session.beginTransaction();
        session.remove(building);
        session.getTransaction().commit();
        return building.toResponse();
    }

    public List<BuildingResponse> getBuildings() {
        return session.createQuery("select b from BuildingEntity b", BuildingEntity.class)
                .getResultList().stream().map(BuildingEntity::toResponse).toList();
    }

    public List<BuildingResponse> getBuildingsFiltered(String filteredBy, String value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(BuildingEntity.class);
        var root = criteria.from(BuildingEntity.class);
        criteria.select(root).where(
                builder.greaterThan(root.get(filteredBy), value)
        );
        session.beginTransaction();
        var buildings = session.createQuery(criteria)
                .getResultList()
                .stream()
                .map(BuildingEntity::toResponse)
                .toList();
        session.getTransaction().commit();
        return buildings;
    }

    public BuildingResponse getBuildingById(Long id) {
        BuildingEntity building = session.get(BuildingEntity.class, id);
        if (building == null) {
            throw new RuntimeException("Patient with id " + id + " not found");
        }
        return building.toResponse();
    }
}
