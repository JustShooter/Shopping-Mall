package by.it.academy.justshooter;

import by.it.academy.justshooter.entity.Discount;
import by.it.academy.justshooter.entity.Item;
import by.it.academy.justshooter.entity.Price;
import by.it.academy.justshooter.entity.Shop;
import by.it.academy.justshooter.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppGet {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
//        Item takenItem = entityManager.find(Item.class, 1);
//        System.out.println(takenItem);

        @SuppressWarnings("unchecked")
        Set<Shop> shopSet = (Set<Shop>) entityManager
                .createQuery("from " + Shop.class.getName())
                .getResultStream()
                .collect(Collectors.toSet());

     /*   ShopItemDiscountWrapper wrapper = (ShopItemDiscountWrapper) entityManager
                .createQuery("from "
                        + ShopItemDiscountWrapper.class.getName()
                        + " where item.id like :id "
                + " and shop.id like :id")
                .setParameter("id", 1)
                .getSingleResult();*/

        /*System.out.println(wrapper.getDiscount());
        System.out.println(wrapper.getItem());
        System.out.println(wrapper.getShop());
        System.out.println(wrapper);
        System.out.println(wrapper);
        System.out.println(wrapper);*/

        for (Shop shop : shopSet) {
            System.out.println();
            System.out.println();
            System.out.println(shop.getShopName());
            Set<Item> items = shop.getItems();
            for (Item item : items) {
                if (item.getShops().contains(shop)) {
                    System.out.print(item + " ");
                }
                List<Price> prices = item.getPrices();
                for (Price price : prices) {
                    if (price.getShops().contains(shop)) {
                        System.out.print(price);
                    }
                }
                Set<Discount> discounts = item.getDiscounts();
                for (Discount discount : discounts) {
                    if(discount.getShops().contains(shop)){
                        System.out.print(discount);
                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }

        entityManager.close();
        HibernateUtil.close();
    }
}
