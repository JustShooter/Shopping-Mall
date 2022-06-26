package by.it.academy.justshooter;

import by.it.academy.justshooter.dao.ItemDaoImpl;
import by.it.academy.justshooter.dao.LocationDaoImpl;
import by.it.academy.justshooter.dao.PriceDaoImpl;
import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.ItemDao;
import by.it.academy.justshooter.dao.interfaces.LocationDao;
import by.it.academy.justshooter.dao.interfaces.PriceDao;
import by.it.academy.justshooter.entity.Item;
import by.it.academy.justshooter.entity.Price;
import by.it.academy.justshooter.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class AppTest {
    public static void main(String[] args) throws NoDataFoundById {
        LocationDao locationDao = new LocationDaoImpl();
        ItemDao itemDao = new ItemDaoImpl();
        PriceDao priceDao = new PriceDaoImpl();

        List<Item> itemsOfShop = itemDao.getItemsOfShop(1);

        itemsOfShop.forEach(System.out::println);

        Price priceIfItemForShop = priceDao.getPriceIfItemForShop(1, 1);
        System.out.println(priceIfItemForShop);

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



    }
}
