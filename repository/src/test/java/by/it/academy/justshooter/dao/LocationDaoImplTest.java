package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.LocationDao;
import by.it.academy.justshooter.entity.Location;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocationDaoImplTest {

    private static final LocationDao locationDao = new LocationDaoImpl();

    @BeforeClass
    public static void setUp() {
        locationDao.create(Location.builder()
                .floor(MockConstants.FLOOR)
                .shopNumber(MockConstants.SHOP_NUMBER_100)
                .description(MockConstants.DESCRIPTION)
                .build());
    }

    @Test
    public void t1_createdAndTakenEntityShouldEqual() throws Exception {
        Location location = locationDao.findOne(MockConstants.ID_1);
        Assert.assertNotNull("Location is not exist in database",
                location);
        Assert.assertEquals("Id is not equals",
                MockConstants.ID_1, location.getId());
        Assert.assertEquals("Description is not equals",
                MockConstants.DESCRIPTION, location.getDescription());
        Assert.assertEquals("Floor not equals",
                MockConstants.FLOOR, location.getFloor());
        Assert.assertEquals("Shop number is not equals",
                MockConstants.SHOP_NUMBER_100, location.getShopNumber());
        Assert.assertNull("Some how shop appears in location, that wired...",
                location.getShop());
    }

    @Test
    public void t2_updateAndCheckIncomingEntry() throws Exception {
        Location location = locationDao.findOne(MockConstants.ID_1);
        Assert.assertNotNull("Location is not exist in database",
                location);
        Assert.assertNull("Some how shop appears in location, that wired...",
                location.getShop());
        location.setDescription(MockConstants.ANOTHER_DESCRIPTION);
        locationDao.update(location);
        Location updatedLocation = locationDao.findOne(MockConstants.ID_1);
        Assert.assertNotNull("Location is not exist in database",
                updatedLocation);
        Assert.assertEquals("Id is not equals",
                location.getId(), updatedLocation.getId());
        Assert.assertEquals("Description is not equals",
                location.getDescription(), updatedLocation.getDescription());
        Assert.assertEquals("Floor not equals",
                location.getFloor(), updatedLocation.getFloor());
        Assert.assertEquals("Shop number is not equals",
                location.getShopNumber(), updatedLocation.getShopNumber());
        Assert.assertNull("Some how shop appears in location, that wired...",
                updatedLocation.getShop());
    }

    @Test
    public void t3_shouldCreateNewDBEntry() {
        Location locationEntity = Location.builder()
                .floor(MockConstants.FLOOR)
                .shopNumber(MockConstants.SHOP_NUMBER_101)
                .description(MockConstants.ANOTHER_DESCRIPTION)
                .build();
        Assert.assertNotEquals("ID should be created after persist",
                MockConstants.ZERO, locationEntity.getId());
        locationDao.create(locationEntity);
        Assert.assertEquals("Id should be 2",MockConstants.ID_2 , locationEntity.getId());
    }

    @Test
    public void t4_shouldDeleteEntity() throws NoDataFoundById {
        Location locationId2 = locationDao.findOne(MockConstants.ID_2);
        locationDao.delete(locationId2);
        NoDataFoundById thrown =
                Assert.assertThrows(NoDataFoundById.class ,
                        () -> locationDao.findOne(locationId2.getId()));
        Assert.assertTrue(thrown.getMessage().contains("No such id found!"));
    }

    @Test
    public void t5_shouldDeleteEntityById() throws NoDataFoundById {
        locationDao.deleteById(MockConstants.ID_1);
        NoDataFoundById thrown =
                Assert.assertThrows(NoDataFoundById.class,
                        () -> locationDao.deleteById(MockConstants.ID_1));
        Assert.assertTrue(thrown.getMessage().contains("No such id found!"));
    }

    @Test(expected = NoDataFoundById.class)
    public void t5_shouldThrowExceptionIfTryToReadNotExistedDBEntry() throws Exception {
        Location location = locationDao.findOne(MockConstants.NOT_EXISTED_ID);
        Assert.fail("Apparently this should not happen");
    }

    @Test(expected = IllegalStateException.class)
    public void t6_ShouldThrowExceptionAfterClosingEntityManager() throws SQLException {
        locationDao.closeAll();
        locationDao.findAll();
        Assert.fail("Mistake! Exception: " + IllegalStateException.class.getSimpleName() + " not thrown!");
    }
}