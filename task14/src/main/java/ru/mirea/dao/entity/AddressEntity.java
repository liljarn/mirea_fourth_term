package ru.mirea.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mirea.dto.AddressResponse;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {
    public AddressEntity(String addressText, Long zipCode) {
        this.addressText = addressText;
        this.zipCode = zipCode;
    }

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "address_text", unique = true, nullable = false)
    private String addressText;

    @Column(name = "zip_code", nullable = false)
    private Long zipCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private List<BuildingEntity> buildings = new ArrayList<>();

    public AddressResponse toResponse() {
        return new AddressResponse(addressId, addressText, zipCode);
    }
}
