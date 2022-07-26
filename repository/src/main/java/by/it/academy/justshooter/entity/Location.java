package by.it.academy.justshooter.entity;

import lombok.AccessLevel;
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
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "shop_number", nullable = false, unique = true)
    private String shopNumber;

    @Column(name = "floor", nullable = false)
    private Integer floor;

    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL)
    private Shop shop;

    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "shopNumber = " + shopNumber + ", " +
                "floor = " + floor + ", " +
                "description = " + description + ")";
    }
}