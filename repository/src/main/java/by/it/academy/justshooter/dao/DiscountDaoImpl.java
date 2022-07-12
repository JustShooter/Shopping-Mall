package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.DiscountDao;
import by.it.academy.justshooter.dao.abstractdao.Dao;
import by.it.academy.justshooter.entity.Discount;

public class DiscountDaoImpl extends Dao<Discount> implements DiscountDao {
    public DiscountDaoImpl() {
        super(Discount.class);
    }
}
