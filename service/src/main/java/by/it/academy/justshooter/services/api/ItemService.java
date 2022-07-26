package by.it.academy.justshooter.services.api;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.DiscountDto;
import by.it.academy.justshooter.dto.ItemDto;
import by.it.academy.justshooter.dto.ItemForShopDto;
import by.it.academy.justshooter.dto.PriceDto;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemService {
    List<ItemDto> getAllItemsOfShopId(Integer shopId);

    PriceDto getPriceOfItemForShop(Integer itemId, Integer shopId);

    DiscountDto getDiscountOfItemForShop(Integer itemId, Integer shopId, Integer priceId);

    List<ItemForShopDto> getAllItemsWithPriceForShop(Integer shopId);

    List<ItemDto> getAllItems();

    ItemDto getItemById(Integer itemId) throws NoDataFoundById;

    void deleteItemById(Integer itemId) throws NoDataFoundById;

    void createNewItem(String itemName, String article, String barcode);

    @Transactional()
    void updateItemData(Integer itemId, String itemName, String article, String barcode)
            throws NoDataFoundById;
}
