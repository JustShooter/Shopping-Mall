package by.it.academy.justshooter;

import by.it.academy.justshooter.entity.Address;
import by.it.academy.justshooter.entity.Discount;
import by.it.academy.justshooter.entity.Item;
import by.it.academy.justshooter.entity.Location;
import by.it.academy.justshooter.entity.Price;
import by.it.academy.justshooter.entity.Shop;
import by.it.academy.justshooter.entity.ShopOwner;
import by.it.academy.justshooter.entity.enums.StreetType;
import by.it.academy.justshooter.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        //        Создание локаций

        Location location1 = Location.builder()
                .floor(1)
                .shopNumber("101")
                .description("Возле главного входа")
                .build();

        Location location2 = Location.builder()
                .floor(1)
                .shopNumber("102")
                .build();

        Location location3 = Location.builder()
                .floor(1)
                .shopNumber("103")
                .build();

        Location location4 = Location.builder()
                .floor(2)
                .shopNumber("201")
                .description("Возле лифта")
                .build();

        Location location5 = Location.builder()
                .floor(1)
                .shopNumber("104")
                .build();


        entityManager.getTransaction().begin();
        entityManager.persist(location1);
        entityManager.persist(location2);
        entityManager.persist(location3);
        entityManager.persist(location4);
        entityManager.persist(location5);
        entityManager.getTransaction().commit();

        //      Создание магазинов

        Shop shopKari = Shop.builder()
                .shopName("Kari kids")
                .shopOwner(ShopOwner.builder()
                        .ownerName("Kari llc")
                        .address(Address.builder()
                                .city("Минск")
                                .streetType(StreetType.ULICA)
                                .street("Плеханова")
                                .buildingNumber("85")
                                .officeNumber("212")
                                .build())
                        .build())
                .location(location1)
                .build();

        Shop shopShagovita = Shop.builder()
                .shopName("Shagovita")
                .shopOwner(ShopOwner.builder()
                        .ownerName("ООО Шаговита")
                        .address(Address.builder()
                                .city("Минск")
                                .streetType(StreetType.ULICA)
                                .street("Глебки")
                                .buildingNumber("25-2")
                                .officeNumber("85A")
                                .build())
                        .build())
                .location(location4)
                .build();

        Shop shopPullBear = Shop.builder()
                .shopName("Pull & Bear")
                .shopOwner(ShopOwner.builder()
                        .ownerName("Пул энд Беар БЛР ООО")
                        .address(Address.builder()
                                .city("Минск")
                                .streetType(StreetType.ULICA)
                                .street("Притыцкого")
                                .buildingNumber("156")
                                .officeNumber("16")
                                .build())
                        .build())
                .location(location5)
                .build();

        entityManager.getTransaction().begin();
        entityManager.persist(shopKari);
        entityManager.persist(shopShagovita);
        entityManager.persist(shopPullBear);
        entityManager.getTransaction().commit();


        //      Создание товаров

        Item item1 = Item.builder()
                .name("Ботинки детские")
                .article("46804A")
                .barcode(691001158136L)
                .build();

        Price price1 = Price.builder()
                .priceValue(25.25)
                .build();

        Price price2 = Price.builder()
                .priceValue(28.99)
                .build();

        price1.setShops(new HashSet<>(Set.of(shopKari)));
        price2.setShops(new HashSet<>(Set.of(shopShagovita)));

        List<Price> prices1 = new ArrayList<>();

        prices1.add(price1);
        prices1.add(price2);

        item1.setPrices(prices1);

        Item item2 = Item.builder()
                .name("Подростковая байка")
                .article("25192B")
                .barcode(481058267813L)
                .build();

        Price price3 = Price.builder()
                .priceValue(49.99)
                .build();

        price3.setShops(new HashSet<>(Set.of(shopPullBear)));

        List<Price> prices2 = new ArrayList<>();

        prices2.add(price3);

        item2.setPrices(prices2);

        entityManager.getTransaction().begin();
        entityManager.persist(item1);
        entityManager.persist(item2);
        entityManager.getTransaction().commit();

        // Мапинг товаров к магазинам

        Set<Item> itemsKari = new LinkedHashSet<>();
        itemsKari.add(item1);
        shopKari.setItems(itemsKari);

        Set<Item> itemsShagovita = new LinkedHashSet<>();
        itemsShagovita.add(item1);
        shopShagovita.setItems(itemsShagovita);

        Set<Item> itemsPullBear = new HashSet<>();
        ;
        itemsPullBear.add(item2);
        shopPullBear.setItems(itemsPullBear);

        entityManager.getTransaction().begin();
        entityManager.merge(shopKari);
        entityManager.merge(shopShagovita);
        entityManager.merge(shopPullBear);
        entityManager.getTransaction().commit();

        // Создание и маппинг скидки

        Discount discount33 = Discount.builder()
                .discountPercentage(33.00)
                .startDate(Date.valueOf("2022-05-02"))
                .endDate(Date.valueOf("2022-05-10"))
                .build();

        Set<Shop> shops = discount33.getShops();
        Set<Item> items = discount33.getItems();
        shops.add(shopKari);
        items.add(item1);

        discount33.setShops(shops);
        discount33.setItems(items);

        Set<Discount> discounts = item1.getDiscounts();
        discounts.add(discount33);
        item1.setDiscounts(discounts);
        Set<Discount> discounts1 = shopKari.getDiscounts();
        discounts1.add(discount33);
        shopKari.setDiscounts(discounts1);


        entityManager.getTransaction().begin();
        entityManager.persist(discount33);
//        entityManager.merge(shopKari);
//        entityManager.merge(item1);
        entityManager.getTransaction().commit();


        entityManager.close();
        HibernateUtil.close();


    }
}
