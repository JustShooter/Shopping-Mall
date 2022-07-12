package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.AddressDto;
import by.it.academy.justshooter.entity.Address;
import by.it.academy.justshooter.entity.ShopOwner;

import java.util.Optional;

public class AddressMapper {

    public static AddressDto mapFrom(Address address) {
        return new AddressDto(
                address.getId(),
                Optional.ofNullable(address.getShopOwner())
                        .map(ShopOwner::getId)
                        .orElse(null),
                address.getCity(),
                address.getStreet(),
                address.getStreetType().getFullName(),
                address.getBuildingNumber(),
                address.getOfficeNumber());
    }

}
