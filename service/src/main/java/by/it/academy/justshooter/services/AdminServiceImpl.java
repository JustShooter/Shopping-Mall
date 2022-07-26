package by.it.academy.justshooter.services;

import by.it.academy.justshooter.dao.AddressDaoImpl;
import by.it.academy.justshooter.dao.CategoryDaoImpl;
import by.it.academy.justshooter.dao.LocationDaoImpl;
import by.it.academy.justshooter.dao.ShopDaoImpl;
import by.it.academy.justshooter.dao.ShopOwnerDaoImpl;
import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.AddressDao;
import by.it.academy.justshooter.dao.interfaces.CategoryDao;
import by.it.academy.justshooter.dao.interfaces.LocationDao;
import by.it.academy.justshooter.dao.interfaces.ShopDao;
import by.it.academy.justshooter.dao.interfaces.ShopOwnerDao;
import by.it.academy.justshooter.dto.AddressDto;
import by.it.academy.justshooter.dto.CategoryDto;
import by.it.academy.justshooter.dto.LocationDto;
import by.it.academy.justshooter.dto.ShopOwnerDto;
import by.it.academy.justshooter.entity.Address;
import by.it.academy.justshooter.entity.Category;
import by.it.academy.justshooter.entity.Location;
import by.it.academy.justshooter.entity.Shop;
import by.it.academy.justshooter.entity.ShopOwner;
import by.it.academy.justshooter.entity.enums.StreetType;
import by.it.academy.justshooter.mapper.AddressMapper;
import by.it.academy.justshooter.mapper.CategoryMapper;
import by.it.academy.justshooter.mapper.LocationMapper;
import by.it.academy.justshooter.mapper.ShopOwnerMapper;
import by.it.academy.justshooter.services.api.AdminService;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {
    private final LocationDao locationDao = new LocationDaoImpl();
    private final ShopOwnerDao shopOwnerDao = new ShopOwnerDaoImpl();
    private final ShopDao shopDao = new ShopDaoImpl();
    private final AddressDao addressDao = new AddressDaoImpl();
    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void createNewLocation(String shopNumber, Integer floor, String description) {
        locationDao.create(Location.builder()
                .shopNumber(shopNumber)
                .floor(floor)
                .description(description)
                .build());
    }

    @Override
    public Integer createNewAddress(String city,
                                    String streetType,
                                    String street,
                                    String buildingNumber,
                                    String officeNumber) {
        Address address = addressDao.create(Address.builder()
                .city(city)
                .streetType(StreetType.valueOf(streetType))
                .street(street)
                .buildingNumber(buildingNumber)
                .officeNumber(officeNumber)
                .build());
        return address.getId();
    }

    @Override
    @Transactional()
    public void createNewShopOwner(String companyName, Integer companyAddressId) throws NoDataFoundById {
        shopOwnerDao.create(ShopOwner.builder()
                .ownerName(companyName)
                .address(addressDao.findOne(companyAddressId))
                .build());
    }

    @Override
    @Transactional()
    public void createNewShop(String shopName, Integer shopOwnerId, Integer locationId, Integer categoryId) throws NoDataFoundById {
        shopDao.create(Shop.builder()
                .shopName(shopName)
                .shopOwner(shopOwnerDao.findOne(shopOwnerId))
                .location(locationDao.findOne(locationId))
                .category(categoryDao.findOne(categoryId))
                .build());
    }


    @Override
    public List<LocationDto> getFreeLocations() {
        return locationDao.getFreeLocations()
                .stream()
                .map(LocationMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShopOwnerDto> getAllShopOwners() {
        return shopOwnerDao
                .findAll()
                .stream()
                .map(ShopOwnerMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional()
    public boolean updateShopData(Integer shopId,
                                  String shopName,
                                  Integer shopLocationId,
                                  Integer shopOwnerId,
                                  Integer shopCategoryId)
            throws NoDataFoundById {
        Shop shop = shopDao.findOne(shopId);
        if (!shop.getShopName().equals(shopName)) {
            shop.setShopName(shopName);
        }
        if (!shop.getLocation().getId().equals(shopLocationId)) {
            shop.setLocation(locationDao.findOne(shopLocationId));
        }
        if (!shop.getShopOwner().getId().equals(shopOwnerId)) {
            shop.setShopOwner(shopOwnerDao.findOne(shopOwnerId));
        }
        if (!shop.getCategory().getId().equals(shopCategoryId)) {
            shop.setCategory(categoryDao.findOne(shopCategoryId));
        }
        Shop update = shopDao.update(shop);
        return shop.equals(update);
    }

    @Override
    public void deleteShopById(Integer shopId) throws NoDataFoundById {
        shopDao.deleteById(shopId);
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        return addressDao.findAll()
                .stream()
                .map(AddressMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional()
    public void deleteShopOwnerById(Integer shopOwnerId) throws NoDataFoundById {
        ShopOwner shopOwner = shopOwnerDao.findOne(shopOwnerId);
        Integer addressId = shopOwner.getAddress().getId();
        shopOwnerDao.delete(shopOwner);
        if (addressDao.findOne(addressId).getShopOwners().isEmpty()) {
            addressDao.deleteById(addressId);
        }
    }

    @Override
    public ShopOwnerDto getShopOwnerById(Integer shopOwnerId) throws NoDataFoundById {
        return ShopOwnerMapper.mapFrom(shopOwnerDao.findOne(shopOwnerId));
    }

    @Override
    @Transactional()
    public void updateShopOwnerData(Integer shopOwnerId, String companyName, Integer companyAddressId) throws NoDataFoundById {
        ShopOwner shopOwner = shopOwnerDao.findOne(shopOwnerId);
        if (!companyName.equals(shopOwner.getOwnerName())) {
            shopOwner.setOwnerName(companyName);
        }
        if (!companyAddressId.equals(shopOwner.getAddress().getId())) {
            Address address = addressDao.findOne(companyAddressId);
            shopOwner.setAddress(address);
        }
        shopOwnerDao.update(shopOwner);
    }

    @Override
    public LocationDto getLocationById(Integer locationId) throws NoDataFoundById {
        return LocationMapper.mapFrom(locationDao.findOne(locationId));
    }

    @Override
    public void deleteLocationById(Integer locationId) throws NoDataFoundById {
        locationDao.deleteById(locationId);
    }

    @Override
    public List<LocationDto> getAllLocations() {
        return locationDao.findAll()
                .stream()
                .map(LocationMapper::mapFrom)
                .sorted(Comparator.comparing(LocationDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional()
    public void updateLocation(Integer locationId,
                               String shopNumber,
                               Integer floor,
                               String description) throws NoDataFoundById {
        Location location = locationDao.findOne(locationId);
        if (!location.getShopNumber().equals(shopNumber)) {
            location.setShopNumber(shopNumber);
        }
        if (!location.getFloor().equals(floor)) {
            location.setFloor(floor);
        }
        if (!location.getDescription().equals(description)) {
            location.setDescription(description);
        }
        locationDao.update(location);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) throws NoDataFoundById {
        return CategoryMapper.mapFrom(categoryDao.findOne(categoryId));
    }

    @Override
    public void deleteCategoryById(Integer categoryId) throws NoDataFoundById {
        categoryDao.deleteById(categoryId);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryDao.findAll()
                .stream()
                .map(CategoryMapper::mapFrom)
                .sorted(Comparator.comparing(CategoryDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public void createNewCategory(String categoryName, String description) {
        categoryDao.create(Category.builder()
                .name(categoryName)
                .description(description)
                .build());
    }

    @Override
    @Transactional()
    public void updateCategoryData(Integer categoryId, String categoryName, String description)
            throws NoDataFoundById {
        Category category = categoryDao.findOne(categoryId);
        if (!category.getName().equals(categoryName)) {
            category.setName(categoryName);
        }
        if (!category.getDescription().equals(description)) {
            category.setDescription(description);
        }
        categoryDao.update(category);
    }
}
