package by.it.academy.justshooter;

import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.services.ItemService;
import by.it.academy.justshooter.services.ShopService;

import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ShopService shopService = new ShopService();
        ItemService itemService = new ItemService();
        List<ShopDto> allShops = shopService.getAllShops();
        allShops.forEach(System.out::println);
        System.out.println();
        for (ShopDto dto : allShops) {
            System.out.println(dto);
            itemService.getAllItemsWithPriceForShop(dto.getId()).forEach(System.out::println);
        }
    }
}