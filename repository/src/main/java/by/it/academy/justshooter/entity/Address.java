package by.it.academy.justshooter.entity;

import by.it.academy.justshooter.entity.enums.StreetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private ShopOwner shopOwner;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Enumerated
    @Column(name = "street_type", nullable = false)
    private StreetType streetType;

    @Column(name = "building_number", nullable = false)
    private String buildingNumber;

    @Column(name = "office_number", nullable = false)
    private String officeNumber;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "city = " + city + ", " +
                "street = " + street + ", " +
                "streetType = " + streetType.getShortName() + ", " +
                "buildingNumber = " + buildingNumber + ", " +
                "officeNumber = " + officeNumber + ")";
    }
}