package by.it.academy.justshooter;

import by.it.academy.justshooter.dto.DiscountDto;
import by.it.academy.justshooter.dto.ItemDto;
import by.it.academy.justshooter.dto.PriceDto;
import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.services.ItemServiceImpl;
import by.it.academy.justshooter.services.ShopServiceImpl;
import by.it.academy.justshooter.services.api.ItemService;
import by.it.academy.justshooter.services.api.ShopService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ShopService shopService = new ShopServiceImpl();
        ItemService itemService = new ItemServiceImpl();
        List<ShopDto> allShops = shopService.getAllShops();
        Map<ShopDto, Map<ItemDto, Map<PriceDto, DiscountDto>>> collect = allShops.stream()
                .collect(Collectors.
                        toMap(shopDto -> shopDto,
                                shopDto -> itemService.getAllItemsOfShopId(shopDto.getId()).stream()
                                        .collect(Collectors.
                                                toMap(itemDto -> itemDto,
                                                        itemDto -> {
                                                            PriceDto priceOfItemForShop = itemService.getPriceOfItemForShop(itemDto.getId(), shopDto.getId());
                                                            return Map.of(priceOfItemForShop,
                                                                    itemService.getDiscountOfItemForShop(itemDto.getId(), shopDto.getId(), priceOfItemForShop.getId()));
                                                        }))));
        System.out.println(collect);
    }
}