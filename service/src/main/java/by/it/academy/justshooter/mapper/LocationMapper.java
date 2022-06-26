package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.LocationDto;
import by.it.academy.justshooter.entity.Location;
import by.it.academy.justshooter.entity.Shop;

import java.util.Optional;

public class LocationMapper {

    public static LocationDto mapFrom(Location location) {
        return new LocationDto(
                location.getId(),
                location.getShopNumber(),
                location.getFloor(),
                Optional.ofNullable(location.getShop())
                        .map(Shop::getId)
                        .orElse(null),
                location.getDescription());
    }

}
