package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.LocationDao;
import by.it.academy.justshooter.entity.Location;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.sql.SQLException;

@FixMethodOrder
public class LocationDaoImplTest {

    public static final Integer ID = 1;
    public static final String SHOP_NUMBER = "100";
    public static final String DESCRIPTION = "Description";
    public static final Integer FLOOR = 1;

    private final LocationDao locationDao = new LocationDaoImpl();


    @Test
    public void findOne() throws SQLException {
        locationDao.create(Location.builder()
                .floor(FLOOR)
                .shopNumber(SHOP_NUMBER)
                .description(DESCRIPTION)
                .build());
        Location location = locationDao.findOne(ID);
        Assert.assertNotNull(location);
        Assert.assertEquals(ID, location.getId());
        Assert.assertEquals(DESCRIPTION, location.getDescription());
        Assert.assertEquals(FLOOR, location.getFloor());
        Assert.assertEquals(SHOP_NUMBER, location.getShopNumber());
    }

    @Test
    public void findAll() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteById() {
    }


    @Test(expected = IllegalStateException.class)
    public void closeAll() throws SQLException {
        locationDao.closeAll();
        locationDao.findAll();
        Assert.fail("Mistake! Exception: " + IllegalStateException.class.getSimpleName() + " not thrown!");
    }
}