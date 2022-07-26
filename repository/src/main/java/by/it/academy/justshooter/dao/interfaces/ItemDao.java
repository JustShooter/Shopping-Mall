package by.it.academy.justshooter.dao.interfaces;

import by.it.academy.justshooter.entity.Discount;
import by.it.academy.justshooter.entity.Item;
import by.it.academy.justshooter.entity.Shop;

import java.util.List;

public interface ItemDao extends DaoInterface<Item> {

    List<Item> getItemsOfShop(Integer shopId);

    Discount getDiscountOfItemForShop(Integer itemDtoId, Integer shopDtoId, Integer priceOfItemForShopId);
}
