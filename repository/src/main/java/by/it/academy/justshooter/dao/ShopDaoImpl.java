package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.ShopDao;
import by.it.academy.justshooter.dao.parentdao.Dao;
import by.it.academy.justshooter.entity.Shop;

public class ShopDaoImpl extends Dao<Shop> implements ShopDao {

    public ShopDaoImpl() {
        super(Shop.class);
    }

}
