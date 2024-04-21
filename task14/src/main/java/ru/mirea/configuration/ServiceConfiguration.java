package ru.mirea.configuration;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mirea.dao.repository.AddressRepository;
import ru.mirea.dao.repository.BuildingRepository;
import ru.mirea.service.address.AddressService;
import ru.mirea.service.address.HibernateAddressService;
import ru.mirea.service.address.JpaAddressService;
import ru.mirea.service.building.BuildingService;
import ru.mirea.service.building.HibernateBuildingService;
import ru.mirea.service.building.JpaBuildingService;
import ru.mirea.service.email.EmailService;

@Configuration
public class ServiceConfiguration {
    @Bean
    @ConditionalOnProperty(name = "orm-type", havingValue = "jpa")
    public AddressService jpaAddressService(
            AddressRepository addressRepository,
            BuildingRepository buildingRepository,
            EmailService emailService
    ) {
        return new JpaAddressService(addressRepository, buildingRepository, emailService);
    }

    @Bean
    @ConditionalOnProperty(name = "orm-type", havingValue = "jpa")
    public BuildingService jpaBuildingService(BuildingRepository buildingRepository, EmailService emailService) {
        return new JpaBuildingService(buildingRepository, emailService);
    }

    @Bean
    @ConditionalOnProperty(name = "orm-type", havingValue = "hibernate")
    public AddressService hibernateAddressService(SessionFactory sessionFactory) {
        return new HibernateAddressService(sessionFactory);
    }

    @Bean
    @ConditionalOnProperty(name = "orm-type", havingValue = "hibernate")
    public BuildingService hibernateBuildingService(SessionFactory sessionFactory) {
        return new HibernateBuildingService(sessionFactory);
    }
}
