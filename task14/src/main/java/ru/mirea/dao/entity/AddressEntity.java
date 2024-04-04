package ru.mirea.dao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Setter
@Getter
@NoArgsConstructor
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
}
