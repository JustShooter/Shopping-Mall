package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.ItemDao;
import by.it.academy.justshooter.dao.abstractdao.Dao;
import by.it.academy.justshooter.entity.Discount;
import by.it.academy.justshooter.entity.Item;

import java.util.List;

public class ItemDaoImpl extends Dao<Item> implements ItemDao {

    public ItemDaoImpl() {
        super(Item.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> getItemsOfShop(Integer shopId) {
        return entityManager.createQuery(
                "select item from ShopItemPriceDiscount i " +
                        "where i.shop.id = :shopId")
                .setParameter("shopId", shopId)
                .getResultList();
    }

    @Override
    public Discount getDiscountOfItemForShop(Integer itemId, Integer shopId, Integer priceId) {
        Discount result = null;
        try {
            result = (Discount) entityManager.createQuery(
                            "select discount from ShopItemPriceDiscount d " +
                                    "where d.item.id = :itemId " +
                                    "and d.shop.id = :shopId " +
                                    "and d.price.id = :priceId")
                    .setParameter("itemId", itemId)
                    .setParameter("shopId", shopId)
                    .setParameter("priceId", priceId)
                    .getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
