package by.it.academy.justshooter.services.api;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.CategoryDto;
import by.it.academy.justshooter.dto.ItemForShopDto;
import by.it.academy.justshooter.dto.ShopDto;

import java.util.List;
import java.util.Map;

public interface ShopService {
    List<ShopDto> getAllShops();

    Map<ShopDto, List<ItemForShopDto>> getAllItemsOfEachShop();

    ShopDto getShopById(Integer shopId) throws NoDataFoundById;

    List<CategoryDto> getAllCategories();

    List<ShopDto> getShopsByCategory(Integer categoryId);
}
