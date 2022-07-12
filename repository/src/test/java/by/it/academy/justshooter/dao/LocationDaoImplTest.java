package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.LocationDao;
import by.it.academy.justshooter.entity.Location;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
        Assert.assertNotNull(MockConstants.LOCATION_IS_NOT_EXIST_IN_DATABASE,
                location);
        Assert.assertEquals(MockConstants.ID_IS_NOT_EQUALS,
                MockConstants.ID_1, location.getId());
        Assert.assertEquals(MockConstants.DESCRIPTION_IS_NOT_EQUALS,
                MockConstants.DESCRIPTION, location.getDescription());
        Assert.assertEquals(MockConstants.FLOOR_NOT_EQUALS,
                MockConstants.FLOOR, location.getFloor());
        Assert.assertEquals(MockConstants.SHOP_NUMBER_IS_NOT_EQUALS,
                MockConstants.SHOP_NUMBER_100, location.getShopNumber());
        Assert.assertNull(MockConstants.SOME_HOW_SHOP_APPEARS_IN_LOCATION_THAT_WIRED,
                location.getShop());
    }

    @Test
    public void t2_updateAndCheckIncomingEntry() throws Exception {
        Location location = locationDao.findOne(MockConstants.ID_1);
        Assert.assertNotNull(MockConstants.LOCATION_IS_NOT_EXIST_IN_DATABASE,
                location);
        Assert.assertNull(MockConstants.SOME_HOW_SHOP_APPEARS_IN_LOCATION_THAT_WIRED,
                location.getShop());
        location.setDescription(MockConstants.ANOTHER_DESCRIPTION);
        locationDao.update(location);
        Location updatedLocation = locationDao.findOne(MockConstants.ID_1);
        Assert.assertNotNull(MockConstants.LOCATION_IS_NOT_EXIST_IN_DATABASE,
                updatedLocation);
        Assert.assertEquals(MockConstants.ID_IS_NOT_EQUALS,
                location.getId(), updatedLocation.getId());
        Assert.assertEquals(MockConstants.DESCRIPTION_IS_NOT_EQUALS,
                location.getDescription(), updatedLocation.getDescription());
        Assert.assertEquals(MockConstants.FLOOR_NOT_EQUALS,
                location.getFloor(), updatedLocation.getFloor());
        Assert.assertEquals(MockConstants.SHOP_NUMBER_IS_NOT_EQUALS,
                location.getShopNumber(), updatedLocation.getShopNumber());
        Assert.assertNull(MockConstants.SOME_HOW_SHOP_APPEARS_IN_LOCATION_THAT_WIRED,
                updatedLocation.getShop());
    }

    @Test
    public void t3_shouldCreateNewDBEntry() {
        Location locationEntity = Location.builder()
                .floor(MockConstants.FLOOR)
                .shopNumber(MockConstants.SHOP_NUMBER_101)
                .description(MockConstants.ANOTHER_DESCRIPTION)
                .build();
        Assert.assertNotEquals(MockConstants.ID_SHOULD_BE_CREATED_AFTER_PERSIST,
                MockConstants.ZERO, locationEntity.getId());
        locationDao.create(locationEntity);
        Assert.assertEquals(MockConstants.ID_SHOULD_BE_2,MockConstants.ID_2 , locationEntity.getId());
    }

    @Test
    public void t4_shouldDeleteEntity() throws NoDataFoundById {
        Location locationId2 = locationDao.findOne(MockConstants.ID_2);
        locationDao.delete(locationId2);
        NoDataFoundById thrown =
                Assert.assertThrows(NoDataFoundById.class ,
                        () -> locationDao.findOne(locationId2.getId()));
        Assert.assertTrue(thrown.getMessage().contains(MockConstants.NO_SUCH_ID_FOUND));
    }

    @Test
    public void t5_shouldDeleteEntityById() throws NoDataFoundById {
        locationDao.deleteById(MockConstants.ID_1);
        NoDataFoundById thrown =
                Assert.assertThrows(NoDataFoundById.class,
                        () -> locationDao.deleteById(MockConstants.ID_1));
        Assert.assertTrue(thrown.getMessage().contains(MockConstants.NO_SUCH_ID_FOUND));
    }

    @Test(expected = NoDataFoundById.class)
    public void t5_shouldThrowExceptionIfTryToReadNotExistedDBEntry() throws Exception {
        Location location = locationDao.findOne(MockConstants.NOT_EXISTED_ID);
        Assert.fail(MockConstants.APPARENTLY_THIS_SHOULD_NOT_HAPPEN);
    }

    @Test(expected = IllegalStateException.class)
    public void t6_ShouldThrowExceptionAfterClosingEntityManager() {
        locationDao.closeAll();
        locationDao.findAll();
        Assert.fail("Mistake! Exception: " + IllegalStateException.class.getSimpleName() + " not thrown!");
    }
}