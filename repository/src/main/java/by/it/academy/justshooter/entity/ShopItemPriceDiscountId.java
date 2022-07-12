package by.it.academy.justshooter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ShopItemPriceDiscountId implements Serializable {
    private static final long serialVersionUID = -5348218737331940501L;
    @Column(name = "item_id", nullable = false)
    private Integer itemId;

    @Column(name = "price_id", nullable = false)
    private Integer priceId;

    @Column(name = "shop_id", nullable = false)
    private Integer shopId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ShopItemPriceDiscountId entity = (ShopItemPriceDiscountId) o;
        return Objects.equals(this.itemId, entity.itemId) &&
                Objects.equals(this.shopId, entity.shopId) &&
                Objects.equals(this.priceId, entity.priceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, shopId, priceId);
    }

}