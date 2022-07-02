package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.ShopOwnerDto;
import by.it.academy.justshooter.entity.Address;
import by.it.academy.justshooter.entity.ShopOwner;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class ShopOwnerMapper {

    public static ShopOwnerDto mapFrom(ShopOwner shopOwner) {
        return new ShopOwnerDto(
                shopOwner.getId(),
                shopOwner.getOwnerName(),
                Optional.ofNullable(shopOwner.getAddress())
                        .map(Address::getId)
                        .orElse(null),
                AddressMapper.mapFrom(shopOwner.getAddress()));
    }

}
