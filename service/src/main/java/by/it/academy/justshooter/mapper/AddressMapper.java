package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.AddressDto;
import by.it.academy.justshooter.entity.Address;
import by.it.academy.justshooter.entity.ShopOwner;

import java.util.Optional;

public class AddressMapper {
    private AddressMapper() {
    }

    public static AddressDto mapFrom(Address address) {
        return new AddressDto(
                address.getId(),
                address.getCity(),
                address.getStreet(),
                address.getStreetType(),
                address.getBuildingNumber(),
                address.getOfficeNumber());
    }

}
