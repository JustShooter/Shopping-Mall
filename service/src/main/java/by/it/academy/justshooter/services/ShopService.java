package by.it.academy.justshooter.services;

import by.it.academy.justshooter.dao.ShopDaoImpl;
import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.ShopDao;
import by.it.academy.justshooter.dto.ItemForShopDto;
import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.mapper.ShopMapper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShopService {

    private final ShopDao shopDao = new ShopDaoImpl();

    public List<ShopDto> getAllShops() {
        return shopDao
                .findAll()
                .stream()
                .map(ShopMapper::mapFrom)
                .sorted(Comparator.comparing(ShopDto::hashCode))
                .collect(Collectors.toList());
    }

    public Map<ShopDto, List<ItemForShopDto>> getAllItemsOfEachShop() {
        ItemService itemService = new ItemService();
        return getAllShops()
                .stream()
                .collect(Collectors.
                        toMap(Function.identity(),
                                shopDto -> itemService.getAllItemsWithPriceForShop(shopDto.getId())));
    }

    public ShopDto getShopById(Integer shopId) throws NoDataFoundById {
        return ShopMapper.mapFrom(shopDao.findOne(shopId));
    }
}
