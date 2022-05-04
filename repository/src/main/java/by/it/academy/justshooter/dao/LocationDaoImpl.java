package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.LocationDao;
import by.it.academy.justshooter.dao.parentdao.Dao;
import by.it.academy.justshooter.entity.Location;

public class LocationDaoImpl extends Dao<Location> implements LocationDao {

    public LocationDaoImpl() {
        super(Location.class);
    }

}
