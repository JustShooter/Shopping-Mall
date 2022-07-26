package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.abstractdao.Dao;
import by.it.academy.justshooter.dao.interfaces.ShopItemPriceDiscountDao;
import by.it.academy.justshooter.entity.ShopItemPriceDiscount;

import java.util.List;

public class ShopItemPriceDiscountDaoImpl extends Dao<ShopItemPriceDiscount> implements ShopItemPriceDiscountDao {
    public ShopItemPriceDiscountDaoImpl() {
        super(ShopItemPriceDiscount.class);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<ShopItemPriceDiscount> getShopItemPriceDiscountListByDiscountGreaterThan(Integer discountValue) {
        return entityManager.createQuery(
                "select s from ShopItemPriceDiscount s " +
                        "left join fetch s.discount " +
                        "left join fetch s.shop as l " +
                        "left join fetch s.item " +
                        "left join fetch s.price " +
                        "left join fetch l.location " +
                        "where s.discount.discountPercentage > :discountValue")
                .setParameter("discountValue", discountValue.doubleValue())
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ShopItemPriceDiscount> getShopItemPriceDiscountListByDiscountLessThan(Integer discountValue) {
        return entityManager.createQuery(
                "select s from ShopItemPriceDiscount s " +
                        "left join fetch s.discount " +
                        "left join fetch s.shop as l " +
                        "left join fetch s.item " +
                        "left join fetch s.price " +
                        "left join fetch l.location " +
                        "where s.discount.discountPercentage < :discountValue")
                .setParameter("discountValue", discountValue.doubleValue())
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ShopItemPriceDiscount> getShopItemPriceDiscountListByDiscountEquals(Integer discountValue) {
        return entityManager.createQuery(
                "select s from ShopItemPriceDiscount as s " +
                        "left join fetch s.discount " +
                        "left join fetch s.shop as l " +
                        "left join fetch s.item " +
                        "left join fetch s.price " +
                        "left join fetch l.location " +
                        "where s.discount.discountPercentage = :discountValue")
                .setParameter("discountValue", discountValue.doubleValue())
                .getResultList();
    }
}

