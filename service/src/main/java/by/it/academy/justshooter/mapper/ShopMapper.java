package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.entity.Location;
import by.it.academy.justshooter.entity.Shop;
import by.it.academy.justshooter.entity.ShopOwner;

import java.util.Optional;

public class ShopMapper {

    public static ShopDto mapFrom(Shop shop) {
        return new ShopDto(
                shop.getId(),
                shop.getShopName(),
                Optional.ofNullable(shop.getLocation())
                        .map(Location::getId)
                        .orElse(null),
                Optional.ofNullable(shop.getShopOwner())
                        .map(ShopOwner::getId)
                        .orElse(null));
    }

}
