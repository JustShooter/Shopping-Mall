package by.it.academy.justshooter.dao.interfaces;

import by.it.academy.justshooter.entity.ShopItemPriceDiscount;

import java.util.List;

public interface ShopItemPriceDiscountDao extends DaoInterface<ShopItemPriceDiscount> {

    List<ShopItemPriceDiscount> getShopItemPriceDiscountListByDiscountGreaterThan(Integer discountValue);

    List<ShopItemPriceDiscount> getShopItemPriceDiscountListByDiscountLessThan(Integer discountValue);

    List<ShopItemPriceDiscount> getShopItemPriceDiscountListByDiscountEquals(Integer discountValue);
}

