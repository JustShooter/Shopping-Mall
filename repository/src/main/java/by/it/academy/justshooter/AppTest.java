package by.it.academy.justshooter;

import by.it.academy.justshooter.dao.CategoryDaoImpl;
import by.it.academy.justshooter.dao.DiscountDaoImpl;
import by.it.academy.justshooter.dao.ShopDaoImpl;
import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.CategoryDao;
import by.it.academy.justshooter.dao.interfaces.DiscountDao;
import by.it.academy.justshooter.dao.interfaces.ShopDao;
import by.it.academy.justshooter.entity.Category;
import by.it.academy.justshooter.entity.Discount;
import by.it.academy.justshooter.entity.Shop;

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

        CategoryDao categoryDao = new CategoryDaoImpl();
        System.out.println(categoryDao.findAll());

        ShopDao shopDao = new ShopDaoImpl();
        List<Shop> shopsByCategoryId = shopDao.getShopsByCategoryId(1);
        System.out.println(shopsByCategoryId);
        shopDao.closeAll();
    }
}
