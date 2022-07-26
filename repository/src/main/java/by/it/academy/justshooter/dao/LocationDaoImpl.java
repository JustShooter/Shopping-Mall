package by.it.academy.justshooter.dao;

import by.it.academy.justshooter.dao.interfaces.LocationDao;
import by.it.academy.justshooter.dao.abstractdao.Dao;
import by.it.academy.justshooter.entity.Location;

import java.util.List;

public class LocationDaoImpl extends Dao<Location> implements LocationDao {

    public LocationDaoImpl() {
        super(Location.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Location> getFreeLocations() {
        return entityManager.createQuery("select l " +
                        "from Location as l " +
                        "left outer join l.shop as s " +
                        "where s is null")
                        .getResultList();
    }
}
