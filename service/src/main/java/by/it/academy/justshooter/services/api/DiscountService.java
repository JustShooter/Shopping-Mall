package by.it.academy.justshooter.services.api;

import by.it.academy.justshooter.dto.ShopItemPriceDiscountDto;

import java.util.List;

public interface DiscountService {
    List<ShopItemPriceDiscountDto> searchDiscount(String filter, Integer discountValue);
}
