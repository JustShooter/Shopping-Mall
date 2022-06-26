package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.PriceDao;
import by.it.academy.justshooter.dao.parentdao.Dao;
import by.it.academy.justshooter.entity.Price;

public class PriceDaoImpl extends Dao<Price> implements PriceDao {

    public PriceDaoImpl() {
        super(Price.class);
    }

    @Override
    public Price getPriceIfItemForShop(Integer itemId, Integer shopId) {
        return (Price) entityManager.createQuery(
                        "select price from ShopItemPriceDiscount i " +
                                "where i.shop.id = :shopId " +
                                "and i.item.id = :itemId")
                .setParameter("shopId", shopId)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }
}
