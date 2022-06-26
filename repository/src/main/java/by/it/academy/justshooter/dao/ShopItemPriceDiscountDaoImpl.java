package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.ShopItemPriceDiscountDao;
import by.it.academy.justshooter.dao.parentdao.Dao;
import by.it.academy.justshooter.entity.ShopItemPriceDiscount;

public class ShopItemPriceDiscountDaoImpl extends Dao<ShopItemPriceDiscount> implements ShopItemPriceDiscountDao {

    protected ShopItemPriceDiscountDaoImpl() {
        super(ShopItemPriceDiscount.class);
    }

}
