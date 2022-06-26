package by.it.academy.justshooter.dao.interfaces;

import by.it.academy.justshooter.entity.Price;

public interface PriceDao extends DaoInterface<Price> {

    Price getPriceIfItemForShop(Integer itemId, Integer shopId);
}
