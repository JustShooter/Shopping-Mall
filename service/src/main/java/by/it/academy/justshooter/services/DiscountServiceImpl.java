package by.it.academy.justshooter.services;

import by.it.academy.justshooter.dao.ShopItemPriceDiscountDaoImpl;
import by.it.academy.justshooter.dao.interfaces.ShopItemPriceDiscountDao;
import by.it.academy.justshooter.dto.ShopItemPriceDiscountDto;
import by.it.academy.justshooter.mapper.ShopItemPriceDiscountMapper;
import by.it.academy.justshooter.services.api.DiscountService;

import java.util.List;
import java.util.stream.Collectors;


public class DiscountServiceImpl implements DiscountService {
    ShopItemPriceDiscountDao discountDao = new ShopItemPriceDiscountDaoImpl();

    @Override
    public List<ShopItemPriceDiscountDto> searchDiscount(String filter, Integer discountValue) {
        if ("GT".equals(filter)) {
            return discountDao.getShopItemPriceDiscountListByDiscountGreaterThan(discountValue)
                    .stream()
                    .map(ShopItemPriceDiscountMapper::mapFrom)
                    .collect(Collectors.toList());
        } else if ("LT".equals(filter)) {
            return discountDao.getShopItemPriceDiscountListByDiscountLessThan(discountValue)
                    .stream()
                    .map(ShopItemPriceDiscountMapper::mapFrom)
                    .collect(Collectors.toList());
        } else if ("EQ".equals(filter)) {
            return discountDao.getShopItemPriceDiscountListByDiscountEquals(discountValue)
                    .stream()
                    .map(ShopItemPriceDiscountMapper::mapFrom)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
