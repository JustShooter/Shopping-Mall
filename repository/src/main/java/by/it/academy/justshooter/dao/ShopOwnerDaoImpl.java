package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.ShopOwnerDao;
import by.it.academy.justshooter.dao.abstractdao.Dao;
import by.it.academy.justshooter.entity.ShopOwner;

public class ShopOwnerDaoImpl extends Dao<ShopOwner> implements ShopOwnerDao {

    public ShopOwnerDaoImpl() {
        super(ShopOwner.class);
    }

}
