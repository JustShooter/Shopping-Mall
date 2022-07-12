package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.DiscountDto;
import by.it.academy.justshooter.entity.Discount;

public class DiscountMapper {

    public static DiscountDto mapFrom(Discount discount) {
        return new DiscountDto(
                discount.getId(),
                discount.getDiscountPercentage(),
                discount.getStartDate(),
                discount.getEndDate());
    }

}
