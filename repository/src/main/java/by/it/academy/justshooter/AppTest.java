package by.it.academy.justshooter;

import by.it.academy.justshooter.dao.LocationDaoImpl;
import by.it.academy.justshooter.dao.interfaces.LocationDao;
import by.it.academy.justshooter.entity.Location;

import java.util.List;

public class AppTest {
    public static void main(String[] args) {
        LocationDao locationDao = new LocationDaoImpl();
        locationDao.closeAll();
        List<Location> all = locationDao.findAll();
        all.forEach(System.out::println);
    }
}
