package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.PriceDto;
import by.it.academy.justshooter.entity.Price;

public class PriceMapper {

    private PriceMapper() {
    }

    public static PriceDto mapFrom(Price price) {
        return new PriceDto(
                price.getId(),
                price.getPriceValue());
    }

}
