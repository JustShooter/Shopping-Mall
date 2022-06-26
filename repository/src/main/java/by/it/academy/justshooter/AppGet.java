package by.it.academy.justshooter;

import by.it.academy.justshooter.entity.Discount;
import by.it.academy.justshooter.entity.Item;
import by.it.academy.justshooter.entity.Price;
import by.it.academy.justshooter.entity.Shop;
import by.it.academy.justshooter.entity.ShopItemPriceDiscount;
import by.it.academy.justshooter.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppGet {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getEntityManager();


        @SuppressWarnings("unchecked")
        Set<Shop> shopSet = (Set<Shop>) entityManager
                .createQuery("from " + Shop.class.getName())
                .getResultStream()
                .collect(Collectors.toSet());

        for (Shop shop : shopSet) {
            System.out.println();
            System.out.print(shop.getShopName());
            System.out.print(" ");
            System.out.println(shop.getLocation());
            Set<ShopItemPriceDiscount> set = shop.getShopItemPriceDiscounts();
            for (ShopItemPriceDiscount itemPriceDiscount : set) {
                System.out.print(itemPriceDiscount.getItem() + " ");
                System.out.print(itemPriceDiscount.getPrice() + " ");
                if (itemPriceDiscount.getDiscount() != null) {
                    System.out.print(itemPriceDiscount.getDiscount() + " ");
                }
                System.out.println();
            }
        }


        /*for (Shop shop : shopSet) {
            System.out.println();
            System.out.print(shop.getShopName());
            System.out.print(" ");
            System.out.println(shop.getLocation());
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
                        System.out.print(" ");
                        System.out.print(discount);
                    }
                }
                System.out.println();
            }
            System.out.println();
        }*/

        entityManager.close();
        HibernateUtil.close();
    }
}
