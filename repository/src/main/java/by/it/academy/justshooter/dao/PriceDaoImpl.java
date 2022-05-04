package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.PriceDao;
import by.it.academy.justshooter.dao.parentdao.Dao;
import by.it.academy.justshooter.entity.Price;

public class PriceDaoImpl extends Dao<Price> implements PriceDao {

    public PriceDaoImpl() {
        super(Price.class);
    }

}
