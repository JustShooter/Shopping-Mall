package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.ShopDao;
import by.it.academy.justshooter.dao.abstractdao.Dao;
import by.it.academy.justshooter.entity.Shop;

import java.util.List;

public class ShopDaoImpl extends Dao<Shop> implements ShopDao {

    public ShopDaoImpl() {
        super(Shop.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Shop> getShopsByCategoryId(Integer categoryId) {
        return entityManager.createQuery(
                        "from Shop s " +
                                "where s.category.id = :categoryId")
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

}
