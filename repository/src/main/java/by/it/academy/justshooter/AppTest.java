package by.it.academy.justshooter;

import by.it.academy.justshooter.dao.*;
import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.*;
import by.it.academy.justshooter.entity.*;
import by.it.academy.justshooter.entity.enums.StreetType;
import by.it.academy.justshooter.util.HibernateUtil;
import liquibase.Liquibase;
import liquibase.database.core.MySQLDatabase;

import java.sql.Date;
import java.util.List;

public class AppTest {
    public static void main(String[] args) throws NoDataFoundById {
     /*   LocationDao locationDao = new LocationDaoImpl();
        ItemDao itemDao = new ItemDaoImpl();
        PriceDao priceDao = new PriceDaoImpl();

        List<Item> itemsOfShop = itemDao.getItemsOfShop(1);

        itemsOfShop.forEach(System.out::println);

        Price priceIfItemForShop = priceDao.getPriceIfItemForShop(1, 1);
        System.out.println(priceIfItemForShop);*/

        /*Location location = Location.builder()
                .floor(1)
                .shopNumber("110")
                .description("Some desc")
                .build();
        System.out.println(location.getId());
        locationDao.create(location);
        System.out.println(location.getId());
        locationDao.deleteById(location.getId());
        System.out.println(location.getId());*/

        /*DiscountDao discountDao = new DiscountDaoImpl();

        discountDao.create(Discount.builder()
                .discountPercentage(10.00)
                .startDate(Date.valueOf("2022-6-30"))
                .endDate(Date.valueOf("2022-7-7"))
                .build());
        discountDao.deleteById(9);

        discountDao.closeAll();*/

        /*CategoryDao categoryDao = new CategoryDaoImpl();
        System.out.println(categoryDao.findAll());

        ShopDao shopDao = new ShopDaoImpl();
        List<Shop> shopsByCategoryId = shopDao.getShopsByCategoryId(1);
        System.out.println(shopsByCategoryId);
        shopDao.closeAll();*/

       /* LocationDao locationDao = new LocationDaoImpl();
        List<Location> freeLocations = locationDao.getFreeLocations();
        System.out.println(freeLocations);*/

       /* ShopDao shopDao = new ShopDaoImpl();
        shopDao.deleteById(1);
        shopDao.closeAll();*/

        ShopOwnerDao shopOwnerDao = new ShopOwnerDaoImpl();
        AddressDao addressDao = new AddressDaoImpl();
/*        ShopDao shopDao = new ShopDaoImpl();
        LocationDao locationDao = new LocationDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();*/

        /*ShopOwner shopOwner = shopOwnerDao.findOne(1);
        Category category = categoryDao.findOne(1);
        Location location = locationDao.findOne(14);

        Shop shop = Shop.builder()
                .shopName("TestShop")
                .shopOwner(shopOwner)
                .category(category)
                .location(location)
                .build();

        shopDao.create(shop);*/
        /*
        Shop one = shopDao.findOne(18);

        one.setCategory(categoryDao.findOne(4));

        shopDao.update(one);
*/
      /* shopOwnerDao.deleteById(12);*/

        List<Address> all = addressDao.findAll();
        System.out.println(all);
    }
}
