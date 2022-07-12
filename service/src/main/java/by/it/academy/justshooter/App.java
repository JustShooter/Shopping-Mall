package by.it.academy.justshooter;

import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.entity.Address;
import by.it.academy.justshooter.entity.Category;
import by.it.academy.justshooter.entity.Location;
import by.it.academy.justshooter.entity.Shop;
import by.it.academy.justshooter.entity.ShopOwner;
import by.it.academy.justshooter.entity.enums.StreetType;
import by.it.academy.justshooter.mapper.ShopMapper;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello world!");
     /*   ShopServiceImpl shopService = new ShopServiceImpl();
        ItemServiceImpl itemService = new ItemServiceImpl();
        List<ShopDto> allShops = shopService.getAllShops();
        allShops.forEach(System.out::println);
        System.out.println();
        for (ShopDto dto : allShops) {
            System.out.println(dto);
            itemService.getAllItemsWithPriceForShop(dto.getId()).forEach(System.out::println);
        }*/
        ShopDto shopDto = ShopMapper.mapFrom(Shop.builder()
                .id(1)
                .shopName("Shop Name")
                .location(Location.builder()
                        .id(1)
                        .shopNumber("111")
                        .floor(1)
                        .description("Some location description")
                        .build())
                .shopOwner(ShopOwner.builder()
                        .id(1)
                        .ownerName("Owner name")
                        .address(Address.builder()
                                .id(1)
                                .city("City")
                                .streetType(StreetType.ULICA)
                                .street("Street")
                                .buildingNumber("11")
                                .officeNumber("222")
                                .build())
                        .build())
                .category(Category.builder()
                        .id(1)
                        .name("Category name")
                        .description("Some category description")
                        .build())
                .build());
        System.out.println(shopDto);
    }
}