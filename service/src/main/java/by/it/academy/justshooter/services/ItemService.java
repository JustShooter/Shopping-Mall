package by.it.academy.justshooter.services;

import by.it.academy.justshooter.dao.ItemDaoImpl;
import by.it.academy.justshooter.dao.PriceDaoImpl;
import by.it.academy.justshooter.dao.interfaces.ItemDao;
import by.it.academy.justshooter.dao.interfaces.PriceDao;
import by.it.academy.justshooter.dto.DiscountDto;
import by.it.academy.justshooter.dto.ItemDto;
import by.it.academy.justshooter.dto.ItemForShopDto;
import by.it.academy.justshooter.dto.PriceDto;
import by.it.academy.justshooter.entity.Discount;
import by.it.academy.justshooter.mapper.DiscountMapper;
import by.it.academy.justshooter.mapper.ItemMapper;
import by.it.academy.justshooter.mapper.PriceMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemService {

    private final ItemDao itemDao = new ItemDaoImpl();
    private final PriceDao priceDao = new PriceDaoImpl();

    public List<ItemDto> getAllItemsOfShopId(Integer shopId) {
        return itemDao
                .getItemsOfShop(shopId)
                .stream()
                .map(ItemMapper::mapFrom)
                .sorted(Comparator.comparing(ItemDto::getName))
                .collect(Collectors.toList());
    }

    public PriceDto getPriceOfItemForShop(Integer itemId, Integer shopId) {
        return PriceMapper.mapFrom(priceDao.getPriceIfItemForShop(itemId, shopId));
    }

    public DiscountDto getDiscountOfItemForShop(Integer itemId, Integer shopId, Integer priceId) {
        return DiscountMapper.mapFrom(Optional
                .ofNullable(itemDao.getDiscountOfItemForShop(itemId, shopId, priceId))
                .orElse(Discount.builder().build()));
    }

    public List<ItemForShopDto> getAllItemsWithPriceForShop(Integer shopId) {
        List<ItemDto> allItemsOfShopId = getAllItemsOfShopId(shopId);
        List<ItemForShopDto> itemForShopDtoList = new ArrayList<>();
        for (ItemDto dto : allItemsOfShopId) {
            DiscountDto discountDto = getDiscountOfItemForShop(dto.getId(), shopId, getPriceOfItemForShop(dto.getId(), shopId).getId());
            itemForShopDtoList.add(new ItemForShopDto(
                    dto.getId(),
                    dto.getName(),
                    dto.getArticle(),
                    dto.getBarcode(),
                    getPriceOfItemForShop(dto.getId(), shopId).getPriceValue(),
                    discountDto.getDiscountPercentage(),
                    discountDto.getStartDate(),
                    discountDto.getEndDate()
            ));
        }
        return itemForShopDtoList;
    }


}
