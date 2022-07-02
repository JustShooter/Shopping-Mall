package by.it.academy.justshooter;

import by.it.academy.justshooter.entity.Address;
import by.it.academy.justshooter.entity.Discount;
import by.it.academy.justshooter.entity.Item;
import by.it.academy.justshooter.entity.Location;
import by.it.academy.justshooter.entity.Price;
import by.it.academy.justshooter.entity.Shop;
import by.it.academy.justshooter.entity.ShopItemPriceDiscount;
import by.it.academy.justshooter.entity.ShopItemPriceDiscountId;
import by.it.academy.justshooter.entity.ShopOwner;
import by.it.academy.justshooter.entity.enums.StreetType;
import by.it.academy.justshooter.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.sql.Date;

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
                .shopNumber("104")
                .description("Возле лифта")
                .build();

        Location location5 = Location.builder()
                .floor(1)
                .shopNumber("105")
                .build();

        Location location6 = Location.builder()
                .floor(2)
                .shopNumber("201")
                .build();


        entityManager.getTransaction().begin();
        entityManager.persist(location1);
        entityManager.persist(location2);
        entityManager.persist(location3);
        entityManager.persist(location4);
        entityManager.persist(location5);
        entityManager.persist(location6);
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

        //      Создание товаров, цен и скидок

        Item item1 = Item.builder()
                .name("Ботинки детские")
                .article("46804A")
                .barcode("691001158136L")
                .build();

        Item item2 = Item.builder()
                .name("Подростковая байка")
                .article("25192B")
                .barcode("481058267813L")
                .build();

        Price price1 = Price.builder()
                .priceValue(25.25)
                .build();

        Price price2 = Price.builder()
                .priceValue(28.99)
                .build();

        Price price3 = Price.builder()
                .priceValue(49.99)
                .build();

        Discount discount33 = Discount.builder()
                .discountPercentage(33.00)
                .startDate(Date.valueOf("2022-06-01"))
                .endDate(Date.valueOf("2022-06-10"))
                .build();

        //Создание связей
        //Можно и эти связи закинуть в БД, и не нужно будет мапить их в сущности

        ShopItemPriceDiscount shopItemPriceDiscount1 =
                ShopItemPriceDiscount.builder()
                        .id(new ShopItemPriceDiscountId())
                        .item(item1)
                        .price(price1)
                        .shop(shopKari)
                        .discount(discount33)
                        .build();
        ShopItemPriceDiscount shopItemPriceDiscount2 =
                ShopItemPriceDiscount.builder()
                        .id(new ShopItemPriceDiscountId())
                        .item(item1)
                        .price(price2)
                        .shop(shopShagovita)
                        .build();
        ShopItemPriceDiscount shopItemPriceDiscount3 =
                ShopItemPriceDiscount.builder()
                        .id(new ShopItemPriceDiscountId())
                        .item(item2)
                        .price(price3)
                        .shop(shopPullBear)
                        .build();

        //Мапинг связей в сущности

        shopKari.getShopItemPriceDiscounts().add(shopItemPriceDiscount1);
        shopShagovita.getShopItemPriceDiscounts().add(shopItemPriceDiscount2);
        shopPullBear.getShopItemPriceDiscounts().add(shopItemPriceDiscount3);
        item1.getShopItemPriceDiscounts().add(shopItemPriceDiscount1);
        item1.getShopItemPriceDiscounts().add(shopItemPriceDiscount2);
        item2.getShopItemPriceDiscounts().add(shopItemPriceDiscount3);
        price1.getShopItemPriceDiscounts().add(shopItemPriceDiscount1);
        price2.getShopItemPriceDiscounts().add(shopItemPriceDiscount2);
        price3.getShopItemPriceDiscounts().add(shopItemPriceDiscount3);
        discount33.getShopItemPriceDiscounts().add(shopItemPriceDiscount1);

//        price1.getShops().add(shopKari);
//        price2.getShops().add(shopShagovita);
//        price1.setShops(new HashSet<>(Set.of(shopKari)));
//        price2.setShops(new HashSet<>(Set.of(shopShagovita)));

//        item1.getPrices().add(price1);
//        item1.getPrices().add(price2);

        /*List<Price> prices1 = new ArrayList<>();
        prices1.add(price1);
        prices1.add(price2);
        item1.setPrices(prices1);*/


//        price3.getShops().add(shopPullBear);

//        price3.setShops(new HashSet<>(Set.of(shopPullBear)));

//        item2.getPrices().add(price3);

        /*List<Price> prices2 = new ArrayList<>();
        prices2.add(price3);
        item2.setPrices(prices2);*/

        entityManager.getTransaction().begin();
        entityManager.persist(item1);
        entityManager.persist(item2);
        entityManager.persist(price1);
        entityManager.persist(price2);
        entityManager.persist(price3);
        entityManager.persist(discount33);
        entityManager.getTransaction().commit();

//        entityManager.getTransaction().begin();
//        entityManager.persist(shopItemPriceDiscount1);
//        entityManager.persist(shopItemPriceDiscount2);
//        entityManager.persist(shopItemPriceDiscount3);
//        entityManager.persist(item1);
//        entityManager.persist(item2);
//        entityManager.getTransaction().commit();

        // Мапинг товаров к магазинам

        /*Set<Item> itemsKari = new LinkedHashSet<>();
        itemsKari.add(item1);
        shopKari.setItems(itemsKari);*/

//        shopKari.getItems().add(item1);

        /*Set<Item> itemsShagovita = new LinkedHashSet<>();
        itemsShagovita.add(item1);
        shopShagovita.setItems(itemsShagovita);*/

//        shopShagovita.getItems().add(item1);

        /*Set<Item> itemsPullBear = new HashSet<>();
        itemsPullBear.add(item2);
        shopPullBear.setItems(itemsPullBear);*/

//        shopPullBear.getItems().add(item2);

//        entityManager.getTransaction().begin();
//        entityManager.merge(shopKari);
//        entityManager.merge(shopShagovita);
//        entityManager.merge(shopPullBear);
//        entityManager.getTransaction().commit();

        // Создание и маппинг скидки


        /*Set<Shop> shops = discount33.getShops();
        Set<Item> items = discount33.getItems();
        shops.add(shopKari);
        items.add(item1);
        discount33.setShops(shops);
        discount33.setItems(items);*/

//        discount33.getShops().add(shopKari);
//        discount33.getItems().add(item1);

        /*Set<Discount> discounts = item1.getDiscounts();
        discounts.add(discount33);
        item1.setDiscounts(discounts);
        Set<Discount> discounts1 = shopKari.getDiscounts();
        discounts1.add(discount33);
        shopKari.setDiscounts(discounts1);*/

//        item1.getDiscounts().add(discount33);
//        shopKari.getDiscounts().add(discount33);


//        entityManager.getTransaction().begin();
//        entityManager.persist(discount33);
//        entityManager.merge(shopKari);
//        entityManager.merge(item1);
//        entityManager.getTransaction().commit();


        entityManager.close();
        HibernateUtil.close();


    }
}
