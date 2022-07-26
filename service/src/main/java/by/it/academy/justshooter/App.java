package by.it.academy.justshooter;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.entity.Address;
import by.it.academy.justshooter.entity.Category;
import by.it.academy.justshooter.entity.Location;
import by.it.academy.justshooter.entity.Shop;
import by.it.academy.justshooter.entity.ShopOwner;
import by.it.academy.justshooter.entity.enums.StreetType;
import by.it.academy.justshooter.mapper.ShopMapper;
import by.it.academy.justshooter.services.AdminServiceImpl;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;

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
        /*ShopDto shopDto = ShopMapper.mapFrom(Shop.builder()
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
        System.out.println(shopDto);*/
       /* AdminServiceImpl adminService = new AdminServiceImpl();
        try {
            adminService.deleteShopOwnerById(14);
        } catch (NoDataFoundById e) {
            e.printStackTrace();
        }*/

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec("password".toCharArray(), salt, 65536, 128);
        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = new byte[0];
        try {
            hash = f.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        Base64.Encoder enc = Base64.getEncoder();
        System.out.printf("salt: %s%n", enc.encodeToString(salt));
        System.out.printf("hash: %s%n", enc.encodeToString(hash));

    }
}