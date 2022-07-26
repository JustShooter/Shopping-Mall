package by.it.academy.justshooter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shopowner")
public class ShopOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "owner_name")
    private String ownerName;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "address_id", nullable = false, unique = true)
    private Address address;

    @Builder.Default
    @OneToMany(mappedBy = "shopOwner", cascade = CascadeType.ALL)
    private Set<Shop> shops = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShopOwner shopOwner = (ShopOwner) o;
        return id != null && Objects.equals(id, shopOwner.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "ownerName = " + ownerName + ", " +
                "address = " + address + ")";
    }
}