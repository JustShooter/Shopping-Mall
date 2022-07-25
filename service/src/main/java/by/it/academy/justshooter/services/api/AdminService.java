package by.it.academy.justshooter.services.api;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.AddressDto;
import by.it.academy.justshooter.dto.CategoryDto;
import by.it.academy.justshooter.dto.LocationDto;
import by.it.academy.justshooter.dto.ShopOwnerDto;

import javax.transaction.Transactional;
import java.util.List;

public interface AdminService {
    void createNewLocation(String shopNumber, Integer floor, String description);

    Integer createNewAddress(String city,
                             String streetType,
                             String street,
                             String buildingNumber,
                             String officeNumber);

    @Transactional()
    void createNewShopOwner(String companyName, Integer companyAddressId) throws NoDataFoundById;

    @Transactional()
    void createNewShop(String shopName, Integer shopOwnerId, Integer locationId, Integer categoryId) throws NoDataFoundById;

    List<LocationDto> getFreeLocations();

    List<ShopOwnerDto> getAllShopOwners();

    @Transactional()
    boolean updateShopData(Integer shopId,
                           String shopName,
                           Integer shopLocationId,
                           Integer shopOwnerId,
                           Integer shopCategoryId)
            throws NoDataFoundById;

    void deleteShopById(Integer shopId) throws NoDataFoundById;

    List<AddressDto> getAllAddresses();

    @Transactional()
    void deleteShopOwnerById(Integer shopOwnerId) throws NoDataFoundById;

    ShopOwnerDto getShopOwnerById(Integer shopOwnerId) throws NoDataFoundById;

    @Transactional()
    void updateShopOwnerData(Integer shopOwnerId, String companyName, Integer companyAddressId) throws NoDataFoundById;

    LocationDto getLocationById(Integer locationId) throws NoDataFoundById;

    void deleteLocationById(Integer locationId) throws NoDataFoundById;

    List<LocationDto> getAllLocations();

    @Transactional()
    void updateLocation(Integer locationId,
                        String shopNumber,
                        Integer floor,
                        String description) throws NoDataFoundById;

    CategoryDto getCategoryById(Integer categoryId) throws NoDataFoundById;

    void deleteCategoryById(Integer categoryId) throws NoDataFoundById;

    List<CategoryDto> getAllCategories();

    void createNewCategory(String categoryName, String description);

    @Transactional()
    void updateCategoryData(Integer categoryId, String categoryName, String description)
            throws NoDataFoundById;
}
