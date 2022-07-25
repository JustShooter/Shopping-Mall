package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.entity.Shop;

public class ShopMapper {

    private ShopMapper() {
    }

    public static ShopDto mapFrom(Shop shop) {
        return new ShopDto(
                shop.getId(),
                shop.getShopName(),
                LocationMapper.mapFrom(shop.getLocation()),
                ShopOwnerMapper.mapFrom(shop.getShopOwner()),
                CategoryMapper.mapFrom(shop.getCategory()));
    }

}
