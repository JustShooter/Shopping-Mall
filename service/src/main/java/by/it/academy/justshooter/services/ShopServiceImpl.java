package by.it.academy.justshooter.services;

import by.it.academy.justshooter.dao.CategoryDaoImpl;
import by.it.academy.justshooter.dao.ShopDaoImpl;
import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.CategoryDao;
import by.it.academy.justshooter.dao.interfaces.ShopDao;
import by.it.academy.justshooter.dto.CategoryDto;
import by.it.academy.justshooter.dto.ItemForShopDto;
import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.mapper.CategoryMapper;
import by.it.academy.justshooter.mapper.ShopMapper;
import by.it.academy.justshooter.services.api.ItemService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShopServiceImpl implements by.it.academy.justshooter.services.api.ShopService {

    private final ShopDao shopDao = new ShopDaoImpl();
    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<ShopDto> getAllShops() {
        return shopDao
                .findAll()
                .stream()
                .map(ShopMapper::mapFrom)
                .sorted(Comparator.comparing(ShopDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Map<ShopDto, List<ItemForShopDto>> getAllItemsOfEachShop() {
        ItemService itemService = new ItemServiceImpl();
        return getAllShops()
                .stream()
                .collect(Collectors.
                        toMap(Function.identity(),
                                shopDto -> itemService.getAllItemsWithPriceForShop(shopDto.getId())));
    }

    @Override
    public ShopDto getShopById(Integer shopId) throws NoDataFoundById {
        return ShopMapper.mapFrom(shopDao.findOne(shopId));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryDao
                .findAll()
                .stream()
                .map(CategoryMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShopDto> getShopsByCategory(Integer categoryId) {
        return shopDao.getShopsByCategoryId(categoryId)
                .stream()
                .map(ShopMapper::mapFrom)
                .collect(Collectors.toList());
    }
}
