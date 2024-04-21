import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mirea.dao.entity.AddressEntity;
import ru.mirea.dao.repository.AddressRepository;
import ru.mirea.dao.repository.BuildingRepository;
import ru.mirea.dto.AddAddressRequest;
import ru.mirea.dto.RemoveAddressRequest;
import ru.mirea.service.address.JpaAddressService;
import ru.mirea.service.email.EmailService;

import java.util.List;
import java.util.Optional;

public class AddressServiceTest {
    private static EmailService emailService;
    private static AddressRepository addressRepository;
    private static BuildingRepository buildingRepository;
    private static List<AddressEntity> entities;

    @BeforeAll
    public static void init() {
        emailService = Mockito.mock(EmailService.class);
        addressRepository = Mockito.mock(AddressRepository.class);
        buildingRepository = Mockito.mock(BuildingRepository.class);
        entities = List.of(
                new AddressEntity(1L, "Вернадского", 123L, List.of()),
                new AddressEntity(2L, "Адмиральского", 234L, List.of()),
                new AddressEntity(3L, "Мичуриснкий", 345L, List.of())
        );
        Mockito.when(addressRepository.findAll()).thenReturn(entities);
    }

    @Test
    @DisplayName("Тестирование JpaAddressService#getAddresses")
    public void getAddressesShouldReturnAllAddresses() {
        JpaAddressService addressService = new JpaAddressService(
                addressRepository,
                buildingRepository,
                emailService
        );
        Assertions.assertThat(
                addressService.getAddresses()
        ).containsAll(entities.stream().map(AddressEntity::toResponse).toList());
    }

    @Test
    @DisplayName("Тестирование JpaAddressService#getById")
    public void getByIdShouldReturnAddress() {
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.ofNullable(entities.get(1)));
        JpaAddressService addressService = new JpaAddressService(
                addressRepository,
                buildingRepository,
                emailService
        );
        Assertions.assertThat(
                addressService.getAddressById(1L).addressText()
        ).isEqualTo(entities.get(1).getAddressText());
    }

    @Test
    @DisplayName("Тестирование JpaAddressService#addAddress")
    public void addAddressShouldReturnRegisteredAddress() {
        JpaAddressService jpaDoctorService = new JpaAddressService(
                addressRepository,
                buildingRepository,
                emailService
        );
        jpaDoctorService.addAddress(new AddAddressRequest("Кировская", 456L));
        Mockito.verify(addressRepository).save(Mockito.any(AddressEntity.class));
    }

    @Test
    @DisplayName("Тестирование JpaAddressService#removeAddressById")
    public void removeAddressByIdShouldRemoveAddress() {
        JpaAddressService addressService = new JpaAddressService(
                addressRepository,
                buildingRepository,
                emailService
        );
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.ofNullable(entities.get(1)));
        RemoveAddressRequest request = new RemoveAddressRequest(1L);
        addressService.removeAddress(request);
        Mockito.verify(addressRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Тестирование JpaAddressService#getFiltered")
    public void getFilteredShouldReturnFiltered() {
        JpaAddressService addressService = new JpaAddressService(
                addressRepository,
                buildingRepository,
                emailService
        );
        List<AddressEntity> filteredEntitites = List.of(
                new AddressEntity(1L, "Вернадского", 123L, List.of()),
                new AddressEntity(3L, "Мичуриснкий", 345L, List.of())
        );
        Mockito.when(addressRepository.findAddressEntitiesByAddressTextGreaterThan("Болотная"))
                .thenReturn(filteredEntitites);
        Assertions.assertThat(
                addressService.getAddressesFiltered("addressText", "Болотная")
        ).containsAll(filteredEntitites.stream().map(AddressEntity::toResponse).toList());
    }
}
