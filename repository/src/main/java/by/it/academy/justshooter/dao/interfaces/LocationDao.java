package by.it.academy.justshooter.dao.interfaces;

import by.it.academy.justshooter.entity.Location;

import java.util.List;

public interface LocationDao extends DaoInterface<Location> {

    List<Location> getFreeLocations();
}
