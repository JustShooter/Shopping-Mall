package by.it.academy.justshooter.services;

import by.it.academy.justshooter.dao.AddressDaoImpl;
import by.it.academy.justshooter.dao.LocationDaoImpl;
import by.it.academy.justshooter.dao.ShopDaoImpl;
import by.it.academy.justshooter.dao.ShopOwnerDaoImpl;
import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.AddressDao;
import by.it.academy.justshooter.dao.interfaces.LocationDao;
import by.it.academy.justshooter.dao.interfaces.ShopDao;
import by.it.academy.justshooter.dao.interfaces.ShopOwnerDao;
import by.it.academy.justshooter.dto.AddressDto;
import by.it.academy.justshooter.dto.LocationDto;
import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.dto.ShopOwnerDto;
import by.it.academy.justshooter.entity.Address;
import by.it.academy.justshooter.entity.Location;
import by.it.academy.justshooter.entity.Shop;
import by.it.academy.justshooter.entity.ShopOwner;
import by.it.academy.justshooter.entity.enums.StreetType;

public class AdminServiceImpl {
    private final LocationDao locationDao = new LocationDaoImpl();
    private final ShopOwnerDao shopOwnerDao = new ShopOwnerDaoImpl();
    private final ShopDao shopDao = new ShopDaoImpl();
    private final AddressDao addressDao = new AddressDaoImpl();

    public void createNewLocation(LocationDto locationDto) {
        locationDao.create(Location.builder()
                .shopNumber(locationDto.getShopNumber())
                .floor(locationDto.getFloor())
                .description(locationDto.getDescription())
                .build());
    }

    public void createNewAddress(AddressDto addressDto) {
        addressDao.create(Address.builder()
                .city(addressDto.getCity())
                .streetType(StreetType.valueOf(addressDto.getStreetType()))
                .street(addressDto.getStreet())
                .buildingNumber(addressDto.getBuildingNumber())
                .officeNumber(addressDto.getOfficeNumber())
                .build());
    }

    public void createNewShopOwner(ShopOwnerDto shopOwnerDto) throws NoDataFoundById {
        shopOwnerDao.create(ShopOwner.builder()
                .ownerName(shopOwnerDto.getOwnerName())
                .address(addressDao.
                        findOne(shopOwnerDto.getAddressId()))
                .build());
    }

    public void createNewShop(ShopDto shopDto) throws NoDataFoundById {
        shopDao.create(Shop.builder()
                .shopName(shopDto.getShopName())
                .shopOwner(shopOwnerDao
                        .findOne(shopDto.getShopOwnerId()))
                .location(locationDao
                        .findOne(shopDto.getLocationId()))
                .build());
    }


}
