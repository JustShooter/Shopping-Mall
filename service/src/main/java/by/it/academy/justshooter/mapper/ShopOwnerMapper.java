package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.ShopOwnerDto;
import by.it.academy.justshooter.entity.Address;
import by.it.academy.justshooter.entity.ShopOwner;

import java.util.Optional;

public class ShopOwnerMapper {

    private ShopOwnerMapper() {
    }

    public static ShopOwnerDto mapFrom(ShopOwner shopOwner) {
        return new ShopOwnerDto(
                shopOwner.getId(),
                shopOwner.getOwnerName(),
                AddressMapper.mapFrom(shopOwner.getAddress()));
    }

}
