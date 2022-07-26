package by.it.academy.justshooter.services;

import by.it.academy.justshooter.dao.ItemDaoImpl;
import by.it.academy.justshooter.dao.PriceDaoImpl;
import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dao.interfaces.ItemDao;
import by.it.academy.justshooter.dao.interfaces.PriceDao;
import by.it.academy.justshooter.dto.DiscountDto;
import by.it.academy.justshooter.dto.ItemDto;
import by.it.academy.justshooter.dto.ItemForShopDto;
import by.it.academy.justshooter.dto.PriceDto;
import by.it.academy.justshooter.entity.Discount;
import by.it.academy.justshooter.entity.Item;
import by.it.academy.justshooter.mapper.DiscountMapper;
import by.it.academy.justshooter.mapper.ItemMapper;
import by.it.academy.justshooter.mapper.PriceMapper;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemServiceImpl implements by.it.academy.justshooter.services.api.ItemService {

    private final ItemDao itemDao = new ItemDaoImpl();
    private final PriceDao priceDao = new PriceDaoImpl();

    @Override
    public List<ItemDto> getAllItemsOfShopId(Integer shopId) {
        return itemDao
                .getItemsOfShop(shopId)
                .stream()
                .map(ItemMapper::mapFrom)
                .sorted(Comparator.comparing(ItemDto::getName))
                .collect(Collectors.toList());
    }

    @Override
    public PriceDto getPriceOfItemForShop(Integer itemId, Integer shopId) {
        return PriceMapper.mapFrom(priceDao.getPriceIfItemForShop(itemId, shopId));
    }

    @Override
    public DiscountDto getDiscountOfItemForShop(Integer itemId, Integer shopId, Integer priceId) {
        return DiscountMapper.mapFrom(Optional
                .ofNullable(itemDao.getDiscountOfItemForShop(itemId, shopId, priceId))
                .orElse(Discount.builder().build()));
    }

    @Override
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

    @Override
    public List<ItemDto> getAllItems() {
        return itemDao.findAll()
                .stream()
                .map(ItemMapper::mapFrom)
                .sorted(Comparator.comparing(ItemDto::getId))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto getItemById(Integer itemId) throws NoDataFoundById {
        return ItemMapper.mapFrom(itemDao.findOne(itemId));
    }

    @Override
    public void deleteItemById(Integer itemId) throws NoDataFoundById {
        itemDao.deleteById(itemId);
    }

    @Override
    public void createNewItem(String itemName, String article, String barcode) {
        itemDao.create(Item.builder()
                .name(itemName)
                .article(article)
                .barcode(barcode)
                .build());
    }

    @Override
    @Transactional()
    public void updateItemData(Integer itemId, String itemName, String article, String barcode)
            throws NoDataFoundById {
        Item item = itemDao.findOne(itemId);
        if (!item.getName().equals(itemName)){
            item.setName(itemName);
        }
        if (!item.getArticle().equals(article)){
            item.setArticle(article);
        }
        if (!item.getBarcode().equals(barcode)){
            item.setBarcode(barcode);
        }
        itemDao.update(item);
    }
}
