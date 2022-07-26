package by.it.academy.justshooter.mapper;

import by.it.academy.justshooter.dto.ShopItemPriceDiscountDto;
import by.it.academy.justshooter.entity.ShopItemPriceDiscount;

public class ShopItemPriceDiscountMapper {
    private ShopItemPriceDiscountMapper() {
    }

    public static ShopItemPriceDiscountDto mapFrom(ShopItemPriceDiscount shopItemPriceDiscount) {
        return new ShopItemPriceDiscountDto(
                shopItemPriceDiscount.getItem().getName(),
                shopItemPriceDiscount.getItem().getArticle(),
                shopItemPriceDiscount.getItem().getBarcode(),
                shopItemPriceDiscount.getPrice().getPriceValue(),
                shopItemPriceDiscount.getShop().getShopName(),
                shopItemPriceDiscount.getShop().getLocation().getShopNumber(),
                shopItemPriceDiscount.getShop().getLocation().getFloor(),
                shopItemPriceDiscount.getShop().getLocation().getDescription(),
                shopItemPriceDiscount.getDiscount().getDiscountPercentage(),
                shopItemPriceDiscount.getDiscount().getStartDate(),
                shopItemPriceDiscount.getDiscount().getEndDate());
    }
}
